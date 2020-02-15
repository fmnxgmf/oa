package cn.fmnx.oa.service.deptService;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.dept.dto.AddDeptDTO;
import cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO;
import cn.fmnx.oa.contoller.dept.vo.DeptVO;

import java.util.List;

public interface DeptService {

    PageResult<DeptVO> findAllDept(PageDTO pageDTO);

    DeptVO findOneDeptById(Long deptId);

    boolean addDept(AddDeptDTO addDeptDTO);

    boolean deleteDept(Long deptId);

    boolean updateDept(AddDeptDTO addDeptDTO);

    List<DeptIdAndNameVO> findAllDeptIdAndName();

}
