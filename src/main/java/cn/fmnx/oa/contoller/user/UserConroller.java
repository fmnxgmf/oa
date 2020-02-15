package cn.fmnx.oa.contoller.user;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.jwt.JwtProperties;
import cn.fmnx.oa.common.jwt.JwtUtils;
import cn.fmnx.oa.common.jwt.config.JwtConfig;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.common.redis.RedisUtil;
import cn.fmnx.oa.common.utils.CookieUtils;
import cn.fmnx.oa.common.utils.MD5Util;
import cn.fmnx.oa.contoller.user.dto.LoginDto;
import cn.fmnx.oa.contoller.user.dto.UserDTO;
import cn.fmnx.oa.contoller.user.vo.UserVO;
import cn.fmnx.oa.entity.user.User;
import cn.fmnx.oa.service.UserService.impl.UserServiceImpl;
import com.github.pagehelper.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName userConroller
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/8
 * @Version V1.0
 **/
@RestController
@EnableConfigurationProperties(JwtProperties.class)
@Api(tags = "用户相关接口")
public class UserConroller {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private UserServiceImpl userService;
    @Value("${oa.jwt.cookieName}")
    private String cookieName;

    @ApiOperation(value = "用户登录接口,不必提交token值，成功返回OA_TOKEN的值", httpMethod = "POST")
    @ApiResponses({
            //@ApiResponse(code = 200,message = "登录成功",responseHeaders = {@ResponseHeader(name = "setCookie",response = Cookie.class)}),
            @ApiResponse(code = 200, message = "登录成功"),
            @ApiResponse(code = 202, message = "登录失败")

    })

    @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "String", paramType = "header")
    @PostMapping("/login")
    public ResultModel login(@RequestBody LoginDto loginDto, HttpServletResponse response, HttpServletRequest request) {
        //1.先判断code验证码是否正确
        String key = loginDto.getDeviceId() + ":" + "code";
        String code = (String) redisUtil.get(key);
        String token = null;
        //System.out.println(code);
        //验证码验证成功
        if (loginDto.getCode().equalsIgnoreCase(code)) {
            //根据用户名和密码查询用户
            loginDto.setPassword(MD5Util.md5(loginDto.getPassword()));
            User user = userService.findUserByNameAndPassword(loginDto.getUserName(), loginDto.getPassword());
            if (!StringUtils.isEmpty(user)) {
                try {
                    token = JwtUtils.generateToken(user, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
                    // 将token写入cookie --- 工厂模式
                    // httpOnly()：避免别的js代码来操作你的cookie，是一种安全措施
                    // charset(): 不需要编码 因为token中没有中文
                    // maxAge()： cookie的生命周期，默认是-1，代表一次会话，浏览器关闭cookie就失效
                    // response: 将cookie写入 --- response中有一个方法 addCookie()
                    // request: cookie中有域的概念 domain 例如一个cookie只能在www.baidu.com生效，无法在别的域下生效
                    // 给cookie绑定一个域，防止别的网站访问你的cookie，也是一种安全措施
                    CookieUtils.newBuilder(response).httpOnly().request(request).build(cookieName, token);
                } catch (Exception e) {
                    throw new OaException(ExceptionEnum.NO_AUTHORIZED_PRO);
                }
            } else {
                //用户名或者密码错误
                throw new OaException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
            }
        } else {
            //图片验证码错误
            throw new OaException(ExceptionEnum.INVALID_VERIFY_CODE);
        }
        Map map = new HashMap();
        map.put(cookieName, token);

        return ResultModel.ok(map);
    }
    /**
     * @MethodName: addUser
     * @Description: 添加用户的接口
     * @Param: [userDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "添加用户的接口")
    @PostMapping("/addUser")
    public ResultModel addUser(@RequestBody UserDTO userDTO){
         boolean flag = userService.addUser(userDTO);
         if (flag){
             return ResultModel.ok("新用户添加成功");
         }else {
             throw new OaException(ExceptionEnum.CREATE_DATA_LIST_ERROR);
         }
    }
    /**
     * @MethodName: updateUser
     * @Description: 修改用户数据
     * @Param: [userDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "修改用户数据，需要带上userId")
    @PutMapping("/updateUser")
    public ResultModel updateUser(@RequestBody @Validated UserDTO userDTO){
        boolean flag =  userService.updateUser(userDTO);
        if (flag){
            return ResultModel.ok("修改用户数据成功");
        }else {
            throw new OaException(ExceptionEnum.UPDATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: findAllUser
     * @Description: 查询所有的用户信息
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.user.vo.UserVO>
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "查询所有的用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码，默认值1",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小，默认值10",required = false,dataType = "int")
    })
    @GetMapping("/findAllUser")
    public ResultModel<PageResult<UserVO>> findAllUser(@RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                       @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
             pageDTO = new PageDTO(pageNum,pageSize);
        }else {
             pageDTO = new PageDTO();
        }

       PageResult<UserVO> pageResult= userService.findAllUser(pageDTO);
       if(!CollectionUtils.isEmpty(pageResult.getItems())){
           return ResultModel.ok(pageResult);
       }else {
           throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
       }
    }
    /**
     * @MethodName: findOneuserById
     * @Description: TODO
     * @Param: [userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.user.vo.UserVO>
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "根据用户id查找用户数据，用于回显和查看")
    @GetMapping("/findOneUserById")
    public ResultModel<UserVO> findOneuserById(@RequestParam("userId")@ApiParam(name = "userId",value = "用户id") Long userId){
        UserVO userVO = userService.findOneUserById(userId);
        Map map = new HashMap(2);
        if (!StringUtils.isEmpty(userVO)){
            map.put("userVO",userVO);
            return ResultModel.ok(map);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: deleteUserBy
     * @Description: 根据用户id删除用户
     * @Param: [userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @DeleteMapping("/deleteUserById")
    @ApiOperation(value = "根据用户id删除用户")
    public ResultModel deleteUserBy(@RequestParam("userId")@ApiParam(name = "userId",value = "用户的id",required = true) Long userId){
        boolean flag = userService.deleteUserById(userId);
        if (flag){
            return ResultModel.ok("删除用户成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: findLikeAllInfo
     * @Description: 根据条件模糊查询，
     * @Param: [condition]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.user.vo.UserVO>
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @ApiOperation(value = "根据姓名,用户名，角色，职位，部门，电话模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition",value = "查询的条件",required = false),
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int")
    })
    @GetMapping("/findLikeUser")
    public ResultModel<PageResult<UserVO>> findLikeAllInfo(@RequestParam("condition") String condition,
                                               @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                               @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }
        PageResult<UserVO> pageResult =userService.findLikeUser(condition,pageDTO);
        if (!CollectionUtils.isEmpty(pageResult.getItems())){
            return ResultModel.ok(pageResult);
        }else {
            return ResultModel.ok("查询数据为空");
        }

    }
}
