package cn.fmnx.oa.mapper.roleMapper;

import cn.fmnx.oa.contoller.role.vo.RoleIdAndNameVO;
import cn.fmnx.oa.entity.role.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    List<RoleIdAndNameVO> findAllRoleIdName();

}
