package cn.fmnx.oa.service.roleService;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.role.dto.RoleDTO;
import cn.fmnx.oa.contoller.role.dto.RolePowerDTO;
import cn.fmnx.oa.contoller.role.vo.RoleIdAndNameVO;
import cn.fmnx.oa.contoller.role.vo.RoleMenuVO;
import cn.fmnx.oa.contoller.role.vo.RoleVO;

import java.util.List;

public interface RoleService {
    List<RoleIdAndNameVO> findAllRoleIdName();

    PageResult<RoleVO> finaAllRole(PageDTO pageDTO);

    boolean updateRole(RoleDTO roleDTO);

    boolean deleteRole(Long roleId);

    RoleVO findRoleById(Long roleId);

    List<RoleMenuVO> findAllMenu();

    boolean setRolePower(RolePowerDTO rolePowerDTO);

    boolean deleteRolePower(RolePowerDTO rolePowerDTO);
}
