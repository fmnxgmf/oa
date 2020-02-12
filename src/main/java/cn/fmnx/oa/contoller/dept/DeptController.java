package cn.fmnx.oa.contoller.dept;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.contoller.dept.dto.AddDeptDTO;
import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.service.deptService.impl.DeptServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DeptController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@RestController
@Api(tags = "部门管理相关接口")
public class DeptController {
    @Autowired
    private DeptServiceImpl deptService;
    /**
     * @MethodName: findAllDept
     * @Description: TODO
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.dept.vo.DeptVO>
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @ApiOperation(value = "查找所有部门信息")
     @GetMapping("/findAllDept")
    public ResultModel<DeptVO> findAllDept(){
       List<DeptVO> depts = deptService.findAllDept();
       Map map = new HashMap(2);
       if (!CollectionUtils.isEmpty(depts)){
           map.put("deptVO",depts);
           return ResultModel.ok(map,depts.size());
       }else {
           throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
       }

    }
    /**
     * @MethodName: findDeptById
     * @Description: 根据部门id回显数据
     * @Param: [deptId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.dept.vo.DeptVO>
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @ApiOperation(value = "根据部门id回显数据")
    @ApiImplicitParam(name = "deptId",value = "部门的id")
    @GetMapping("/findDeptById")
    public ResultModel<DeptVO> findOneDeptById(@RequestParam("deptId")Long deptId){
       DeptVO deptVO = deptService.findOneDeptById(deptId);
       Map map = new HashMap(2);
       if (! StringUtils.isEmpty(deptVO)){
           map.put("deptVO",deptVO);
           return ResultModel.ok(map);
       }else {
           throw new OaException(ExceptionEnum.ECHO_DATA_FAIL);
       }

    }

    /**
     * @MethodName: addDept
     * @Description: TODO
     * @Param: [addDeptDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    public ResultModel addDept(@RequestBody AddDeptDTO addDeptDTO){
        return ResultModel.ok();
    }
}
