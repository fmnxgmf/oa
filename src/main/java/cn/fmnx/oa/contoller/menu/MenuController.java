package cn.fmnx.oa.contoller.menu;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.contoller.menu.vo.MenuVO;
import cn.fmnx.oa.entity.user.User;
import cn.fmnx.oa.service.UserService.UserService;
import cn.fmnx.oa.service.UserService.impl.UserServiceImpl;
import cn.fmnx.oa.service.menuService.impl.MenuServiceImpl;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MenuController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/
@RestController
@Api(tags = "系统菜单相关接口")
public class MenuController {
    @Autowired
    private MenuServiceImpl menuService;
    @Autowired
    private UserServiceImpl userService;
    /**
     * @MethodName: finaAllSeeParentMenu
     * @Description: 查找所有可以显示的父级菜单
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/10
    **/
    @ApiOperation(value = "获取该用户的所有父级菜单,需要提交token值", httpMethod = "GET")
    @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header")
    @ApiResponse(code = 200,message = "成功",response = MenuVO.class)
    @GetMapping("/getAllParentMenu")
    public ResultModel<MenuVO> finaAllSeeParentMenu(HttpServletRequest request){
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        uid = 1;//TODO
        //1.获取user，然后获取role_id
        User user = userService.findUserByUid(uid);
        if(StringUtils.isEmpty(user)){
            throw new OaException(ExceptionEnum.NOT_FOUND_USER);
        }
        Map map = new HashMap(2);
        List<MenuVO> allSeeParentMenu ;
        if(user.getRoleId() != null){
            allSeeParentMenu = menuService.findAllSeeParentMenu(user);
            map.put("menuVOS",allSeeParentMenu);
        }else{
            throw new OaException(ExceptionEnum.NOT_FOUND_USER_ROLE_ID);
        }
        return ResultModel.ok(map,allSeeParentMenu.size());
    }
    @ApiOperation(value = "获取该父级菜单下的子菜单，需要token值",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "token",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "parentId",value = "父级菜单的id值",required = true)
    })
    @GetMapping("/getAllSonMenu")
    public ResultModel<MenuVO> finaAllSeeSonMenu(@RequestParam("parentId") Integer parentId,HttpServletRequest request){

        Integer uid = (Integer) request.getSession().getAttribute("uid");
        uid = 1;
        User user = userService.findUserByUid(uid);
        if(StringUtils.isEmpty(user)){
            throw new OaException(ExceptionEnum.NOT_FOUND_USER);
        }
        if(user.getRoleId()== null){
            throw new OaException(ExceptionEnum.NOT_FOUND_USER_ROLE_ID);
        }
        Map map = new HashMap(2);
        List<MenuVO> menuVOS = new ArrayList<>();
        menuVOS =  menuService.findAllSeeSonMenu(user.getRoleId(),parentId);
        map.put("menuVOS",menuVOS);
        return ResultModel.ok(map,menuVOS.size());
    }
}
