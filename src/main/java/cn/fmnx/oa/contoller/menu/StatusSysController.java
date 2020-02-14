package cn.fmnx.oa.contoller.menu;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.contoller.menu.dto.AddStatusDTO;
import cn.fmnx.oa.contoller.menu.vo.StatusListVO;
import cn.fmnx.oa.service.stastuSysService.impl.StatuSysServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StatusSysController
 * @Description: 状态管理的相关操作
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@RestController
@Api(tags = "状态管理的相关接口")
public class StatusSysController {
    @Autowired
    private StatuSysServiceImpl statuSysService;
    /**
     * @MethodName: addStatusList
     * @Description: 添加状态管理的接口 add
     * @Param: [addStatusDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.menu.vo.StatusListVO>
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @ApiOperation(value = "添加状态管理的接口")
    @PostMapping("/addStatuList")
    public ResultModel<StatusListVO> addStatusList(@RequestBody AddStatusDTO addStatusDTO){
        boolean flag =  statuSysService.addStatusList(addStatusDTO);
        if (flag){
            return ResultModel.ok("数据添加成功");
        }else {
            throw new OaException(ExceptionEnum.CREATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: deleteStatusList
     * @Description: 删除状态管理的接口
     * @Param: [statusId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @ApiOperation(value = "删除状态管理的接口")
    @ApiImplicitParam(name = "statusId",value = "状态类的id",required = true)
    @DeleteMapping("/deleteStatus")
    public ResultModel deleteStatusList(@RequestParam("statusId") Long statusId){
        boolean flag = statuSysService.deleteStatusList(statusId);
        if(flag){
            return  ResultModel.ok("数据删除成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: updateStatusList
     * @Description: 修改状态管理的数据接口，这个需要带上修改的id值
     * @Param: [addStatusDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @ApiOperation(value = "修改状态管理的数据接口，这个需要带上修改的id值")
    @PutMapping("/updateStatus")
    public ResultModel updateStatusList(@RequestBody AddStatusDTO addStatusDTO){
        boolean flag = statuSysService.updateStatusList(addStatusDTO);
        if(flag){
            return  ResultModel.ok("数据修改成功");
        }else {
            throw new OaException(ExceptionEnum.UPDATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: findStatusById
     * @Description: 根据id值返回该数据，用户修改数据时，回显数据
     * @Param: [statusId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.menu.vo.StatusListVO>
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @ApiOperation(value = "根据id值回显数据")
    public ResultModel<StatusListVO> findStatusById(@RequestParam("statusId") Long statusId){
        StatusListVO statusListVO = statuSysService.findStatusById(statusId);
        Map map = new HashMap(2);
        if(! StringUtils.isEmpty(statusListVO)){
            map.put("statusListVo",statusListVO);
            return ResultModel.ok(map);
        }else {
            throw new OaException(ExceptionEnum.ECHO_DATA_FAIL);
        }
    }
    /**
     * @MethodName: findAllStatusList
     * @Description: 查询状态管理的所有数据
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.menu.vo.StatusListVO>
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @ApiOperation(value = "查询状态管理的所有数据")
    @GetMapping("/findAllStatus")
    public ResultModel<StatusListVO> findAllStatusList(){
        Map map = new HashMap(2);
        List<StatusListVO> list = statuSysService.findAllStatusList();
        if(!CollectionUtils.isEmpty(list)){
            map.put("statusListVo",list);
            return ResultModel.ok(map,list.size());
        }else {
            throw new OaException(ExceptionEnum.FAILD_FIND_STATUS);
        }
    }
    /**
     * @MethodName: findStatusByNameOrModel
     * @Description: 根据状态名称或者模块来模糊查询
     * @Param: [nameOrModel]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.menu.vo.StatusListVO>
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "根据状态名称或者模块来模糊查询")
    @ApiImplicitParam(name = "nameOrModel",value = "接收模糊查询的条件可以是名称或者模块,为空则查询所有",required = false)
    @GetMapping("/findStatusByNameOrModel")
    public ResultModel<StatusListVO> findStatusByNameOrModel(@RequestParam("nameOrModel")String nameOrModel){
        List<StatusListVO> statusListVOS = statuSysService.findStatusByNameOrModel(nameOrModel);
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(statusListVOS)){
            map.put("statusListVOS",statusListVOS);
            return ResultModel.ok(map,statusListVOS.size());
        }else {
            return  ResultModel.ok(ExceptionEnum.FIND_DATA_ISEMPTY.getMsg());
        }
    }
}
