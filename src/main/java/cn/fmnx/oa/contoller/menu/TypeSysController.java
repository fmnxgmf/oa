package cn.fmnx.oa.contoller.menu;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.menu.dto.AddTypeListDTO;
import cn.fmnx.oa.contoller.menu.vo.TypeListVO;
import cn.fmnx.oa.entity.menu.SystemTypeList;
import cn.fmnx.oa.service.typeSysService.impl.TypeSysServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.description.type.TypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TypeSysController
 * @Description: 菜单栏的类型管理
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/
@RestController
@Api(tags = "菜单栏的类型管理接口")
public class TypeSysController {
    @Autowired
    private TypeSysServiceImpl typeSysService;
    /**
     * @MethodName: getAllTypeList
     * @Description: 进入类型管理界面展示所有的内容
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/10
    **/
    @ApiOperation(value = "进入类型管理界面展示所有内容的接口",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码，默认值1",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小，默认值10",required = false,dataType = "int")
    })
    @GetMapping("/sysType")
    public ResultModel<PageResult<TypeListVO>> getAllTypeList(@RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                  @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO = new PageDTO(pageNum,pageSize);
       PageResult<TypeListVO> list =typeSysService.findAllTypeList(pageDTO);
        return ResultModel.ok(list);
    }
    /**
     * @MethodName: addTypeList
     * @Description: 类型管理添加类型的接口
     * @Param: [addTypeListDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/11
    **/
    @ApiOperation(value = "类型管理添加类型的接口",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header"),
    })
    @PostMapping("/addTypeList")
    public ResultModel addTypeList(@RequestBody  AddTypeListDTO addTypeListDTO){
        SystemTypeList typeList = new SystemTypeList();
        typeList.setTypeColor(addTypeListDTO.getTypeColor());
        typeList.setTypeModel(addTypeListDTO.getTypeModel());
        typeList.setTypeName(addTypeListDTO.getTypeName());
        typeList.setTypeSortValue(addTypeListDTO.getTypeSortValue());
        boolean flag = typeSysService.addTypeList(typeList);
        if(flag){
            return ResultModel.ok("数据添加成功");
        }else {
            throw new OaException(ExceptionEnum.CREATE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: findOneTypeById
     * @Description: 修改类型管理的数据回显接口，查看改模板数据的接口
     * @Param: [typeId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.menu.vo.TypeListVO>
     * @Author: gmf
     * @Date: 2020/2/11
    **/
    @ApiOperation(value = "修改类型管理的数据回显接口,以及查看改模板数据的接口，需要传递该id值来回显数据")
    @ApiImplicitParam(name = "typeId",value = "修改该类型的id值",required = true)
    @GetMapping("/findOneTypeById")
    public ResultModel<TypeListVO> findOneTypeById(@RequestParam("typeId")Integer typeId){
        TypeListVO typeListVO= typeSysService.findOneTypeById(typeId);
        Map map = new HashMap(2);
        map.put("typeListVO",typeListVO);
        return ResultModel.ok(map,1);
    }
    @PutMapping("/updateTypeList")
    @ApiOperation(value = "修改类型管理数据提交修改接口")
    public ResultModel editTypeList(@RequestBody AddTypeListDTO addTypeListDTO){
        SystemTypeList typeList = new SystemTypeList();
        typeList.setTypeColor(addTypeListDTO.getTypeColor());
        typeList.setTypeModel(addTypeListDTO.getTypeModel());
        typeList.setTypeName(addTypeListDTO.getTypeName());
        typeList.setTypeSortValue(addTypeListDTO.getTypeSortValue());
        typeList.setTypeId(addTypeListDTO.getTypeId());
        //TODO 修改数据未保存
        //带有id则是修改数据
        boolean flag = typeSysService.editTypeList(typeList);
        if (flag){
            return ResultModel.ok("数据修改成功");
        }else {
            throw new OaException(ExceptionEnum.UPDATE_DATA_LIST_ERROR);
        }

    }
    /**
     * @MethodName: deleteTypeListById
     * @Description: 删除管理类型数据的接口
     * @Param: [typeId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/12
    **/
    @ApiOperation(value = "删除管理类型数据的接口")
    @ApiImplicitParam(value = "删除该类型的id值",name = "typeId",required = true)
    @DeleteMapping("/deleteType")
    public ResultModel deleteTypeListById(@RequestParam("typeId")Long typeId){
        boolean flag = typeSysService.deleteTypeListByID(typeId);
        if (flag){
            return ResultModel.ok("删除成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_DATA_LIST_ERROR);
        }
    }
    @ApiOperation(value = "根据类型管理的名称或者模块来模糊查询")
    @ApiImplicitParam(name = "typeNameOrMode",value = "查询类型管理按模块或者名称查询，参数为空查所有",required = false)
    @GetMapping("/findTypeByNameOrModel")
    public ResultModel<TypeListVO> findTypeListLikeByNameOrModel(@RequestParam("typeNameOrMode") String typeNameOrMode){
        List<TypeListVO> list = typeSysService.findTypeListLikeByNameOrModel(typeNameOrMode);
        Map map = new HashMap(2);
        if(!CollectionUtils.isEmpty(list)){
            map.put("typeListVOS",list);
            return ResultModel.ok(map,list.size());
        }
        return  ResultModel.ok(ExceptionEnum.FIND_DATA_ISEMPTY.getMsg());
    }
}
