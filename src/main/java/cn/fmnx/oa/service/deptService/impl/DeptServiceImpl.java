package cn.fmnx.oa.service.deptService.impl;

import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.entity.dept.Dept;
import cn.fmnx.oa.mapper.deptMapper.DeptMapper;
import cn.fmnx.oa.service.deptService.DeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
