package cn.fmnx.oa.service.typeSysService;

import cn.fmnx.oa.contoller.menu.vo.TypeListVO;
import cn.fmnx.oa.entity.menu.SystemTypeList;

import java.util.List;

/**
 * @ClassName TypeSysService
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/
public interface TypeSysService {
    List<TypeListVO> findAllTypeList();

    boolean addTypeList(SystemTypeList typeList);

    boolean editTypeList(SystemTypeList typeList);

    TypeListVO findOneTypeById(Integer typeId);

    boolean deleteTypeListByID(Long typeId);

    List<TypeListVO> findTypeListLikeByNameOrModel(String typeNameOrMode);
}
