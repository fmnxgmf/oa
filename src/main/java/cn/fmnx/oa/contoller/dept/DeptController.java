package cn.fmnx.oa.contoller.dept;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.dept.dto.AddDeptDTO;
import cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO;
import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.service.deptService.impl.DeptServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int")
    })
     @GetMapping("/findAllDept")
    public ResultModel<PageResult<DeptVO>> findAllDept(@RequestParam(value = "pageNum",required = false) Integer pageNum,
                                           @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }
        PageResult<DeptVO> pageResult = deptService.findAllDept(pageDTO);

       if (!CollectionUtils.isEmpty(pageResult.getItems())){
           return ResultModel.ok(pageResult);
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
     * @Description: 添加部门的接口
     * @Param: [addDeptDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @PostMapping("/addDept")
    @ApiOperation(value = "添加部门的接口")
    public ResultModel addDept(@RequestBody @Validated AddDeptDTO addDeptDTO){
        boolean flag = deptService.addDept(addDeptDTO);
        if (flag){
            return ResultModel.ok("数据添加成功");
        }else {
            throw new OaException(ExceptionEnum.CREATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: deleteDept
     * @Description: 删除部门数据的接口
     * @Param: [deptId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "删除部门管理数据的接口")
    @ApiImplicitParam(name = "deptId",value = "部门id",required = true)
    @DeleteMapping("/deleteDept")
    public ResultModel deleteDept(@RequestParam("deptId")Long deptId){
         boolean flag =deptService.deleteDept(deptId);
        if (flag){
            return ResultModel.ok("数据删除成功");
        }else {
            throw new OaException(ExceptionEnum.UPDATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: updateDept
     * @Description: 修改部门管理数据的接口
     * @Param: [addDeptDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "修改部门管理数据的接口")
    @PutMapping("/updateDept")
    public ResultModel updateDept(@RequestBody @Validated AddDeptDTO addDeptDTO){
        boolean flag =  deptService.updateDept(addDeptDTO);
        if (flag){
            return ResultModel.ok("数据修改成功");
        }else {
            throw new OaException(ExceptionEnum.UPDATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: findAllDeptIdAndName
     * @Description: 查询部门的id和对应的名称
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO>
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @ApiOperation(value = "查询部门的id和对应的名称")
    @GetMapping("findAllDeptIdName")
    public ResultModel<DeptIdAndNameVO> findAllDeptIdAndName(){
       List<DeptIdAndNameVO> deptIdAndNameVOS = deptService.findAllDeptIdAndName();
       Map map = new HashMap(2);
       if (! CollectionUtils.isEmpty(deptIdAndNameVOS)){
           map.put("deptIdAndNameVOS",deptIdAndNameVOS);
           return ResultModel.ok(map,deptIdAndNameVOS.size());
       }else {
           throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
       }
    }
}
