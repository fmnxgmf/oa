package cn.fmnx.oa.mapper.deptMapper;

import cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO;
import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.entity.dept.Dept;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @MethodName: DeptMapper
 * @Description: TODO
 * @Param:
 * @Return:
 * @Author: gmf
 * @Date: 2020/2/12
**/
public interface DeptMapper extends Mapper<Dept> {

    List<DeptVO> findAllDept();

    List<DeptIdAndNameVO> findAllDeptIdAndName();
}
