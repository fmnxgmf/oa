package cn.fmnx.oa.service.roleService;

import cn.fmnx.oa.contoller.role.vo.RoleIdAndNameVO;

import java.util.List;

public interface RoleService {
    List<RoleIdAndNameVO> findAllRoleIdName();
}
