package cn.fmnx.oa.contoller.role;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.contoller.role.vo.RoleIdAndNameVO;
import cn.fmnx.oa.service.roleService.RoleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
