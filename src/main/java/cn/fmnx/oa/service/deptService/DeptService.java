package cn.fmnx.oa.service.deptService;

import cn.fmnx.oa.contoller.dept.vo.DeptVO;

import java.util.List;

public interface DeptService {

    List<DeptVO> findAllDept();

    DeptVO findOneDeptById(Long deptId);
}
