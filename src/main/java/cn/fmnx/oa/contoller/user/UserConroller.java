package cn.fmnx.oa.contoller.user;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.jwt.JwtProperties;
import cn.fmnx.oa.common.jwt.JwtUtils;
import cn.fmnx.oa.common.jwt.config.JwtConfig;
import cn.fmnx.oa.common.redis.RedisUtil;
import cn.fmnx.oa.common.utils.CookieUtils;
import cn.fmnx.oa.contoller.user.dto.LoginDto;
import cn.fmnx.oa.entity.user.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

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
    @Value("${oa.jwt.cookieName}")
    private String cookieName;
    @ApiOperation(value = "用户登录接口,不必提交token值，成功返回OA_TOKEN的值",httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200,message = "登录成功",responseHeaders = {@ResponseHeader(name = "setCookie",response = Cookie.class)}),
            @ApiResponse(code = 202,message = "登录失败")

    })

    @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "String",paramType="header")
    @PostMapping("/login")
    public ResultModel login(@RequestBody  LoginDto loginDto, HttpServletResponse response, HttpServletRequest request){
        //1.先判断code验证码是否正确
        String key =loginDto.getDeviceId()+":"+"code";
        String code = (String)redisUtil.get(key);
        String token = null;
        System.out.println(code);
        //验证码验证成功
        if(loginDto.getCode().equalsIgnoreCase("1234")){
            User user = new User(2,"gmf");
            if(loginDto.getUserName().equals("gmf") && loginDto.getPassword().equals("gmf123")){
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
                    throw new OaException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
                }
            }else {
                return ResultModel.error("用户名或密码错误！");
            }
        }else {
            return ResultModel.error("图片验证码校验失败");
        }
        return ResultModel.ok(token);
    }
}
