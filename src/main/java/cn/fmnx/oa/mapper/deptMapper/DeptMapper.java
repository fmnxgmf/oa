package cn.fmnx.oa.mapper.deptMapper;

import cn.fmnx.oa.contoller.dept.dto.ChangeDeptPositionDTO;
import cn.fmnx.oa.contoller.dept.dto.ChangeManagerDTO;
import cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO;
import cn.fmnx.oa.contoller.dept.vo.DeptUserVO;
import cn.fmnx.oa.contoller.dept.vo.DeptVO;
import cn.fmnx.oa.contoller.position.vo.PositionIdNameVO;
import cn.fmnx.oa.entity.dept.Dept;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
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

    List<DeptUserVO> findUserBydeptId(@Param("deptId") Long deptId);

    DeptUserVO findDeptManagerById(@Param("deptId")Long deptId);

    List<PositionIdNameVO> findPositionByDeptId(@Param("deptId")Long deptId);

    Integer changeDeptAndPosition(@Param("changeDeptPositionDTO") ChangeDeptPositionDTO changeDeptPositionDTO);

    Integer changeManagerOne(@Param("changeManagerDTO") ChangeManagerDTO changeManagerDTO);

    Integer changeManagerTwo(@Param("changeManagerDTO")ChangeManagerDTO changeManagerDTO);

}
