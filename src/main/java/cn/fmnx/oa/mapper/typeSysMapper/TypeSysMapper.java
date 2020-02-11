package cn.fmnx.oa.mapper.typeSysMapper;

import cn.fmnx.oa.contoller.menu.vo.TypeListVO;
import cn.fmnx.oa.entity.menu.SystemTypeList;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName TypeSysMapper
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/

/**
 * type_id`
 * type_color
 * type_model
 * type_name`
 * sort_value
 */
public interface TypeSysMapper extends Mapper<SystemTypeList> {
    /**
     * @MethodName: findAllTypeList
     * @Description: 查询类型管理的所有数据
     * @Param: []
     * @Return: java.util.List<cn.fmnx.oa.contoller.menu.vo.TypeListVO>
     * @Author: gmf
     * @Date: 2020/2/10
    **/
    @Select("select type.type_id ,type.type_model,type.type_name,type.sort_value as typeSortValue " +
            "from aoa_type_list as type order by if(isnull(type.sort_value),1,0),type.sort_value asc ")
    List<TypeListVO> findAllTypeList();


}
