package cn.fmnx.oa.contoller.menu;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.contoller.menu.dto.AddMenuDTO;
import cn.fmnx.oa.contoller.menu.vo.MenuVO;
import cn.fmnx.oa.contoller.menu.vo.ParentMenuVO;
import cn.fmnx.oa.entity.menu.Menu;
import cn.fmnx.oa.entity.user.User;
import cn.fmnx.oa.service.UserService.UserService;
import cn.fmnx.oa.service.UserService.impl.UserServiceImpl;
import cn.fmnx.oa.service.menuService.impl.MenuServiceImpl;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
@Api(tags = "系统管理相关接口首页数据展示,菜单管理模块")
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
    /**
     * @MethodName: findAllMenus
     * @Description: 查找所有菜单数据接口
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.menu.vo.MenuVO>
     * @Author: gmf
     * @Date: 2020/2/11
    **/
    @ApiOperation(value = "查找所有菜单数据接口")
    @GetMapping("/findAllMenus")
    public ResultModel<MenuVO> findAllMenus(){
        List<MenuVO> list = menuService.findAllMenus();
        Map map = new HashMap(2);
        if (! CollectionUtils.isEmpty(list)){
            map.put("MenuVOS",list);
            return ResultModel.ok(map,list.size());
        }else {
            throw new OaException(ExceptionEnum.FAILD_FIND_MENUS);
        }
    }
    /**
     * @MethodName: addMenu
     * @Description: 添加菜单的接口
     * @Param: [addMenuDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/11
    **/
    @ApiOperation(value = "添加菜单的接口")
    @PostMapping("/addMenu")
    public ResultModel addMenu(@RequestBody AddMenuDTO addMenuDTO){
        Menu menu = new Menu();
        //主键不用管，自增主键，添加后会自己回显
        menu.setMenuIcon(addMenuDTO.getMenuIcon());
        menu.setMenuName(addMenuDTO.getMenuName());
        menu.setParentId(addMenuDTO.getParentId());
        menu.setISshow(addMenuDTO.getIsShow());;
        menu.setSortId(addMenuDTO.getSortId());
        menu.setMenuUrl(addMenuDTO.getMenuUrl());
       boolean flag = menuService.addMenu(menu);
       if (flag){
           return ResultModel.ok("新增菜单数据成功");
       }else {
           throw new OaException(ExceptionEnum.FAIL_ADD_MENU);
       }

    }
    /**
     * @MethodName: findParentMenu
     * @Description: 新增菜单所需要的父级菜单的id，name值
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.menu.vo.ParentMenuVO>
     * @Author: gmf
     * @Date: 2020/2/11
    **/
    @GetMapping("/findParentMenus")
    @ApiOperation(value = "新增菜单所需要的父级菜单的id，name值")
    public ResultModel<ParentMenuVO> findParentMenu(){
        List<ParentMenuVO> list =menuService.findParent();
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(list)){
            map.put("parentMenuVOS",list);
            return ResultModel.ok(map,list.size());
        }else {
            throw new OaException(ExceptionEnum.NOT_FOUND_PARENT_MENUS);
        }
    }
    /**
     * @MethodName: findOneMenuById
     * @Description: 根据menuId查找某个菜单的所有展示内容可以用来回显数据
     * @Param: [menuId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.menu.vo.MenuVO>
     * @Author: gmf
     * @Date: 2020/2/11
    **/
    @ApiOperation(value = "根据menuId查找某个菜单的所有展示内容可以用来回显数据")
    @ApiImplicitParam(name = "menuId",value = "某个菜单项的id值",required = true)
    @GetMapping("findOneMenuById")
    public ResultModel<MenuVO> findOneMenuById(@RequestParam("menuId") Long menuId){
        MenuVO menuVO = menuService.findOneMenuById(menuId);
        Map map = new HashMap(2);
        if(! StringUtils.isEmpty(menuVO)){
            map.put("menuVO",menuVO);
            return ResultModel.ok(map,1);
        }else{
            throw new OaException(ExceptionEnum.FIND_ONEMENU_BYID_ERROR);
        }
    }
    /**
     * @MethodName: editMenu
     * @Description: 修改菜单提交修改后数据的接口
     * @Param: [addMenuDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/11
    **/
    @ApiOperation(value = "修改菜单提交修改后数据的接口")
    @PutMapping("/updateMenu")
    public ResultModel editMenu(@RequestBody AddMenuDTO addMenuDTO){
        Menu menu = new Menu();
        menu.setMenuId(addMenuDTO.getMenuId());
        menu.setISshow(addMenuDTO.getIsShow());
        menu.setMenuUrl(addMenuDTO.getMenuUrl());
        menu.setSortId(addMenuDTO.getSortId());
        menu.setMenuName(addMenuDTO.getMenuName());
        menu.setMenuIcon(addMenuDTO.getMenuIcon());
        menu.setParentId(addMenuDTO.getParentId());
        boolean flag = menuService.updateMenu(menu);
        if(flag){
            return  ResultModel.ok("数据修改成功!");
        }else {
            throw new OaException(ExceptionEnum.UPDATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: deleteMenu
     * @Description: 根据menuID删除某个菜单的接口
     * @Param: [menuId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/11
    **/
    @ApiOperation(value = "根据menuID删除某个菜单的接口")
    @ApiImplicitParam(name = "menuId",value = "菜单的id",required = true)
    @DeleteMapping("/deleteMenu")
    public ResultModel deleteMenu(@RequestParam("menuId")Long menuId){
        boolean flag =menuService.deleteMenu(menuId);
        if (flag){
            return ResultModel.ok("删除数据成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_DATA_LIST_ERROR);
        }
    }/**
     * @MethodName: deleteMenu
     * @Description: 菜单位置上下移动的接口
     * @Param: [menuId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/11
    **/
    @ApiOperation(value = "菜单位置上下移动的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId",value = "该移位菜单的父级菜单id" ,required = true),
            @ApiImplicitParam(name = "sortId",value = "该移位菜单的排序值" ,required = true),
            @ApiImplicitParam(name = "menuId",value = "该移位菜单的自己id值" ,required = true),
            @ApiImplicitParam(name = "step",value = "该移位菜单的移动,向上移为正，向下移为负",required = true)
    })
    @PutMapping("/changeSortId")
    public ResultModel changeSortId(@RequestParam("parentId") Long parentId,@RequestParam("sortId")Integer sortId,@RequestParam("menuId")Long menuId,@RequestParam("step")Integer step){
       boolean flag = menuService.changeSortId(parentId,sortId,menuId,step);
       if (flag){
           return ResultModel.ok("菜单移动成功");
       }else {
           throw new OaException(ExceptionEnum.MOVE_MENU_FAIL);
       }
    }
    /**
     * @MethodName: findMenuByLikeName
     * @Description: 根据菜单名称查询菜单模糊查询
     * @Param: [menuName]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.entity.menu.Menu>
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @ApiOperation(value = "根据菜单名称查询菜单模糊查询")
    @ApiImplicitParam(name = "menuName",value = "菜单的名称,名称为空则查询所有")
    @GetMapping("/findMenuByLikeName")
    public ResultModel<Menu> findMenuByLikeName(@RequestParam("menuName")String menuName){
        List<MenuVO> menus = menuService.findMenuByLikeName(menuName);
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(menus)){
            map.put("menus",menus);
            return ResultModel.ok(map,menus.size());
        }
        return ResultModel.ok(ExceptionEnum.FIND_DATA_ISEMPTY.getMsg());
    }
}
