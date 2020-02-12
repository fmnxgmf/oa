package cn.fmnx.oa.service.typeSysService.impl;

import cn.fmnx.oa.contoller.menu.vo.TypeListVO;
import cn.fmnx.oa.entity.menu.SystemTypeList;
import cn.fmnx.oa.mapper.typeSysMapper.TypeSysMapper;
import cn.fmnx.oa.service.typeSysService.TypeSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TypeSysServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/
@Service
@Transactional
public class TypeSysServiceImpl implements TypeSysService {
    @Resource
    private TypeSysMapper typeSysMapper;
    @Override
    public List<TypeListVO> findAllTypeList() {
        List<TypeListVO> allTypeList = typeSysMapper.findAllTypeList();
        return allTypeList;
    }

    @Override
    public boolean addTypeList(SystemTypeList typeList) {
        int insert = typeSysMapper.insert(typeList);
        if(insert > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean editTypeList(SystemTypeList typeList) {
        int i = typeSysMapper.updateByPrimaryKey(typeList);
        if(i > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public TypeListVO findOneTypeById(Integer typeId) {
        SystemTypeList type = typeSysMapper.selectByPrimaryKey(typeId);
        TypeListVO typeListVO = new TypeListVO();
        typeListVO.setTypeId(type.getTypeId());
        typeListVO.setTypeModel(type.getTypeModel());
        typeListVO.setTypeName(type.getTypeName());
        typeListVO.setTypeSortValue(type.getTypeSortValue());
        return typeListVO;
    }

    @Override
    public boolean deleteTypeListByID(Long typeId) {
        int i = typeSysMapper.deleteByPrimaryKey(typeId);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<TypeListVO> findTypeListLikeByNameOrModel(String typeNameOrMode) {

        return typeSysMapper.findTypeListLikeByNameOrModel(typeNameOrMode);
    }
}
