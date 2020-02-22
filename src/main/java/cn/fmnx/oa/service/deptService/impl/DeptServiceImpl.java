package cn.fmnx.oa.service.deptService.impl;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.dept.dto.AddDeptDTO;
import cn.fmnx.oa.contoller.dept.dto.ChangeDeptPositionDTO;
import cn.fmnx.oa.contoller.dept.dto.ChangeManagerDTO;
import cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO;
import cn.fmnx.oa.contoller.dept.vo.DeptUserVO;
import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.contoller.position.vo.PositionIdNameVO;
import cn.fmnx.oa.entity.dept.Dept;
import cn.fmnx.oa.mapper.deptMapper.DeptMapper;
import cn.fmnx.oa.service.deptService.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
    public PageResult<DeptVO> findAllDept(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<DeptVO>  deptVOS = deptMapper.findAllDept();
        PageResult<DeptVO> pageResult = new PageResult<>(new PageInfo<DeptVO>(deptVOS));
        return pageResult;
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

    @Override
    public PageResult<DeptUserVO> findUserByDeptId(Long deptId, PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<DeptUserVO> list = deptMapper.findUserBydeptId(deptId);
        PageResult<DeptUserVO> pageResult = new PageResult<>(new PageInfo<DeptUserVO>(list));
        return pageResult;
    }

    @Override
    public DeptUserVO findDeptManagerById(Long deptId) {
        return deptMapper.findDeptManagerById(deptId);

    }

    @Override
    public List<PositionIdNameVO> findPositionByDeptId(Long deptId) {
       return deptMapper.findPositionByDeptId(deptId);

    }

    @Override
    public boolean changeDeptAndPosition(ChangeDeptPositionDTO changeDeptPositionDTO) {
        Integer flag = deptMapper.changeDeptAndPosition(changeDeptPositionDTO);
        if (flag > 0 && flag !=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean changeManager(ChangeManagerDTO changeManagerDTO) {
        //1.先原部门经理职位变动
        Integer flag2 =0;
        Integer flag = deptMapper.changeManagerOne(changeManagerDTO);
        if (flag > 0){
            //2.变动完成后在给原部门设置经理
           flag2  = deptMapper.changeManagerTwo(changeManagerDTO);
        }
        if (flag > 0 && flag2 >0){
            return true;
        }else {
            //手动事务强制回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }


    }
}
