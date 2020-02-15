package cn.fmnx.oa.contoller.role;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.role.dto.RoleDTO;
import cn.fmnx.oa.contoller.role.dto.RolePowerDTO;
import cn.fmnx.oa.contoller.role.vo.RoleIdAndNameVO;
import cn.fmnx.oa.contoller.role.vo.RoleMenuVO;
import cn.fmnx.oa.contoller.role.vo.RoleVO;
import cn.fmnx.oa.service.roleService.impl.RoleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@RestController
@Api(tags = "角色管理相关接口")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;
    /**
     * @MethodName: findAllRoleIdName
     * @Description: 查询角色id和角色名称的接口
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.role.vo.RoleIdAndNameVO>
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @ApiOperation(value = "查询角色id和角色名称的接口")
    @GetMapping("/findAllRoleIdName")
    public ResultModel<RoleIdAndNameVO> findAllRoleIdName(){
        List<RoleIdAndNameVO> roleIdAndNameVOS = roleService.findAllRoleIdName();
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(roleIdAndNameVOS)){
            map.put("roleIdAndNameVOS",roleIdAndNameVOS);
            return ResultModel.ok(map,roleIdAndNameVOS.size());
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: findAllRole
     * @Description: 查找所有的角色数据
     * @Param: [pageNum, pageSize]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.common.page.PageResult<cn.fmnx.oa.contoller.role.vo.RoleVO>>
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @ApiOperation(value = "查找所有的role数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int")
    })
    @GetMapping("/findAllRole")
    public ResultModel<PageResult<RoleVO>> findAllRole(@RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                       @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }
        PageResult<RoleVO> pageResult = roleService.finaAllRole(pageDTO);
        if(!CollectionUtils.isEmpty(pageResult.getItems())){
            return ResultModel.ok(pageResult);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: updateRole
     * @Description: 修改角色
     * @Param: [roleDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @ApiOperation(value = "修改角色名，权限值不能更改")
    @PutMapping("/updateRole")
    public ResultModel updateRole(@RequestBody RoleDTO roleDTO){
       boolean flag = roleService.updateRole(roleDTO);
       if (flag){
           return ResultModel.ok("数据修改成功");
       }else {
           throw new OaException(ExceptionEnum.UPDATE_DATA_LIST_ERROR);
       }
    }
    /**
     * @MethodName: deleteRole
     * @Description: 删除角色role的接口
     * @Param: [roleId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @ApiOperation(value = "删除角色role的接口，只需传入roleID即可" )
    @DeleteMapping("/deleteRole")
    public ResultModel deleteRole(@RequestParam("roleId") Long roleId){
        boolean flag = roleService.deleteRole(roleId);
        if (flag){
            return ResultModel.ok("数据删除成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: findRoleById
     * @Description: 根据roleId查询role
     * @Param: [roleId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.role.vo.RoleVO>
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @GetMapping("/findRoleById")
    @ApiOperation(value = "根据roleId查询role，查看或者回显数据" )
    public ResultModel<RoleVO> findRoleById(@RequestParam("roleId") Long roleId){
        RoleVO roleVO = roleService.findRoleById(roleId);
        Map map = new HashMap(2);
        if (!StringUtils.isEmpty(roleVO)){
            map.put("roleVO",roleVO);
            return ResultModel.ok(map);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: findAllMenu
     * @Description: 给角色设置权限的接口
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.role.vo.RoleMenuVO>
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @GetMapping("/findAllRoleMenu")
    @ApiOperation(value = "给角色设置权限所需的菜单数据查询的接口")
    public ResultModel<RoleMenuVO> findAllMenu(){
        List<RoleMenuVO> roleMenuVOS = roleService.findAllMenu();
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(roleMenuVOS)){
            map.put("roleMenuVOS",roleMenuVOS);
            return ResultModel.ok(map,roleMenuVOS.size());
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: setRolePower
     * @Description: 给角色设置权限的接口
     * @Param: [rolePowerDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/15
    **/
    @ApiOperation(value = "给角色设置权限的接口")
    @PostMapping("/setRolePower")
    public ResultModel setRolePower(@RequestBody RolePowerDTO rolePowerDTO){
        boolean flag = roleService.setRolePower(rolePowerDTO);
        if (flag){
            return ResultModel.ok("权限设置成功");
        }else {
            throw new OaException(ExceptionEnum.SET_ROLE_POWER_ERROR);
        }
    }
    @ApiOperation(value = "删除该角色的某个权限，用角色id和菜单id来确定删除某项")
    @DeleteMapping("/deleteRolePower")
    public ResultModel deleteRolePower(@RequestBody RolePowerDTO rolePowerDTO){
       boolean flag = roleService.deleteRolePower(rolePowerDTO);
        if (flag){
            return ResultModel.ok("权限删除成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_ROLE_POWER_ERROR);
        }
    }

}
