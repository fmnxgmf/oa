package cn.fmnx.oa.service.deptService;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.dept.dto.AddDeptDTO;
import cn.fmnx.oa.contoller.dept.dto.ChangeDeptPositionDTO;
import cn.fmnx.oa.contoller.dept.dto.ChangeManagerDTO;
import cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO;
import cn.fmnx.oa.contoller.dept.vo.DeptUserVO;
import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.contoller.position.vo.PositionIdNameVO;

import java.util.List;

public interface DeptService {

    PageResult<DeptVO> findAllDept(PageDTO pageDTO);

    DeptVO findOneDeptById(Long deptId);

    boolean addDept(AddDeptDTO addDeptDTO);

    boolean deleteDept(Long deptId);

    boolean updateDept(AddDeptDTO addDeptDTO);

    List<DeptIdAndNameVO> findAllDeptIdAndName();

    PageResult<DeptUserVO> findUserByDeptId(Long deptId, PageDTO pageDTO);

    DeptUserVO findDeptManagerById(Long deptId);

    List<PositionIdNameVO> findPositionByDeptId(Long deptId);

    boolean changeDeptAndPosition(ChangeDeptPositionDTO changeDeptPositionDTO);

    boolean changeManager(ChangeManagerDTO changeManagerDTO);
}
