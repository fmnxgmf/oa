package cn.fmnx.oa.contoller.position;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.position.dto.PositionDTO;
import cn.fmnx.oa.contoller.position.vo.PositionIdNameVO;
import cn.fmnx.oa.contoller.position.vo.PositionVO;
import cn.fmnx.oa.service.positiionService.impl.PositionServiceImpl;
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
 * @ClassName PositionController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
@RestController
@Api(tags = "职位管理相关接口")
public class PositionController {
    @Autowired
    private PositionServiceImpl positionService;
    /**
     * @MethodName: findAllPostion
     * @Description: 查找所有职位的信息
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.position.vo.PositionVO>
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "查找所有职位的信息")
    @GetMapping("/findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int")
    })
    public ResultModel<PageResult<PositionVO>> findAllPostion(@RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                              @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }
        PageResult<PositionVO> pageResult = positionService.findAllPosition(pageDTO);

        if (! CollectionUtils.isEmpty(pageResult.getItems())){
            return ResultModel.ok(pageResult);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: findOnePositionByID
     * @Description: 根据职位id回显该职位的信息
     * @Param: [positionId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.position.vo.PositionVO>
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "根据职位id回显该职位的信息")
    @ApiImplicitParam(name = "positionId",value = "职位的id",required = true)
    @GetMapping("/findOnePositionByID")
    public ResultModel<PositionVO> findOnePositionByID(@RequestParam("positionId") Long positionId){
        PositionVO positionVO = positionService.findOnePositionByID(positionId);
        if (!StringUtils.isEmpty(positionVO)){
            return ResultModel.ok(positionVO);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: addPosition
     * @Description: 新增职位的接口
     * @Param: [positionDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "新增职位的接口")
    @PostMapping("/addPositon")
    public ResultModel addPosition(@RequestBody PositionDTO positionDTO){
        boolean flag = positionService.addPositon(positionDTO);
        if (flag){
            return ResultModel.ok("新增职位添加成功");
        }else {
            throw new OaException(ExceptionEnum.CREATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: updatePosition
     * @Description: 修改职位的接口
     * @Param: [positionDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @ApiOperation(value = "修改职位的接口,需要提交id")
    @PutMapping("/updatePosition")
    public ResultModel updatePosition(@RequestBody PositionDTO positionDTO){
        boolean flag = positionService.updatePosition(positionDTO);
        if (flag){
            return ResultModel.ok("职位修改成功");
        }else {
            throw new OaException(ExceptionEnum.UPDATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: findAllPositionIdName
     * @Description: TODO
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.position.vo.PositionIdNameVO>
     * @Author: gmf
     * @Date: 2020/2/14
    **/
    @ApiOperation(value = "查询职位id职位名称")
    @GetMapping("/findAllPositionIdName")
    public ResultModel<PositionIdNameVO> findAllPositionIdName(){
        List<PositionIdNameVO> positionIdNameVOS = positionService.findAllPositionIdName();
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(positionIdNameVOS)){
            map.put("positionIdNameVOS",positionIdNameVOS);
            return ResultModel.ok(map,positionIdNameVOS.size());
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }

}
