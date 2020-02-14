package cn.fmnx.oa.service.deptService.impl;

import cn.fmnx.oa.common.utils.CopyListutils;
import cn.fmnx.oa.contoller.dept.dto.AddDeptDTO;
import cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO;
import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.entity.dept.Dept;
import cn.fmnx.oa.mapper.deptMapper.DeptMapper;
import cn.fmnx.oa.service.deptService.DeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DeptServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;
    @Override
    public List<DeptVO> findAllDept() {
       List<DeptVO>  deptVOS = deptMapper.findAllDept();

        return deptVOS;
    }

    @Override
    public DeptVO findOneDeptById(Long deptId) {
        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        DeptVO deptVO = new DeptVO();
        BeanUtils.copyProperties(dept,deptVO);
        return deptVO;
    }

    @Override
    public boolean addDept(AddDeptDTO addDeptDTO) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(addDeptDTO,dept);
        if (!StringUtils.isEmpty(dept)){
            int insert = deptMapper.insert(dept);
            if (insert > 0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteDept(Long deptId) {
        int i = deptMapper.deleteByPrimaryKey(deptId);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDept(AddDeptDTO addDeptDTO) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(addDeptDTO,dept);
        if (!StringUtils.isEmpty(dept)){
            int i = deptMapper.updateByPrimaryKey(dept);
            if (i > 0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<DeptIdAndNameVO> findAllDeptIdAndName() {
       List<DeptIdAndNameVO>  deptIdAndNameVOS = deptMapper.findAllDeptIdAndName();
        return deptIdAndNameVOS;
    }
}
