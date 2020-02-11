package cn.fmnx.oa.contoller.menu;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
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
    @ApiImplicitParam(name = "token",value = "token",required = true,dataType = "String",paramType = "header")
    @GetMapping("/sysType")
    public ResultModel<TypeListVO> getAllTypeList(){
        Map map = new HashMap(2);
       List<TypeListVO> list =typeSysService.findAllTypeList();
       map.put("typeListVO",list);
        return ResultModel.ok(map,list.size());
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
            throw new OaException(ExceptionEnum.CREATE_TYPE_LIST_ERROR);
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
            throw new OaException(ExceptionEnum.UPDATE_TYPE_LIST_ERROR);
        }

    }
    @ApiOperation(value = "删除管理类型数据的接口")
    @ApiImplicitParam(value = "删除该类型的id值",name = "typeId",required = true)
    @DeleteMapping("/deleteType")
    public ResultModel deleteTypeListById(@RequestParam("typeId")Long typeId){
        boolean flag = typeSysService.deleteTypeListByID(typeId);
        if (flag){
            return ResultModel.ok("删除成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_TYPE_LIST_ERROR);
        }

    }

}
