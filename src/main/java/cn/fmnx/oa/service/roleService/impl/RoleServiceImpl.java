package cn.fmnx.oa.service.roleService.impl;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.common.utils.CopyListutils;
import cn.fmnx.oa.contoller.role.dto.RoleDTO;
import cn.fmnx.oa.contoller.role.dto.RolePowerDTO;
import cn.fmnx.oa.contoller.role.vo.RoleIdAndNameVO;
import cn.fmnx.oa.contoller.role.vo.RoleMenuVO;
import cn.fmnx.oa.contoller.role.vo.RoleVO;
import cn.fmnx.oa.entity.role.Role;
import cn.fmnx.oa.entity.role.Rolepowerlist;
import cn.fmnx.oa.mapper.menuMapper.MenuMapper;
import cn.fmnx.oa.mapper.roleMapper.RoleMapper;
import cn.fmnx.oa.mapper.roleMapper.RolePowerMapper;
import cn.fmnx.oa.service.roleService.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RolePowerMapper rolePowerMapper;
    @Override
    public List<RoleIdAndNameVO> findAllRoleIdName() {
        List<RoleIdAndNameVO> roleIdAndNameVOS = roleMapper.findAllRoleIdName();
        return roleIdAndNameVOS;
    }

    @Override
    public PageResult<RoleVO> finaAllRole(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<Role> roles = roleMapper.selectAll();
        List<RoleVO> roleVOS = new ArrayList<>();
        CopyListutils.copyListBeanUtils(roles,roleVOS,RoleVO.class);
        if (!CollectionUtils.isEmpty(roleVOS)){
            return new PageResult<RoleVO>(new PageInfo<RoleVO>(roleVOS));
        }
        return null;
    }

    @Override
    public boolean updateRole(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO,role);
        if (!StringUtils.isEmpty(role)){
            int i = roleMapper.updateByPrimaryKey(role);
            if (i >0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteRole(Long roleId) {
        int i = roleMapper.deleteByPrimaryKey(roleId);
        if (i > 0){
            return true;
        }

        return false;
    }

    @Override
    public RoleVO findRoleById(Long roleId) {
        Role role = roleMapper.selectByPrimaryKey(roleId);
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(role,roleVO);
        if (! StringUtils.isEmpty(roleVO)){
            return roleVO;
        }
        return null;
    }

    @Override
    public List<RoleMenuVO> findAllMenu() {
        List<RoleMenuVO> roleMenuVOS = menuMapper.findRoleMenus();

        return roleMenuVOS;
    }

    @Override
    public boolean setRolePower(RolePowerDTO rolePowerDTO) {
        Integer integer = rolePowerMapper.insertRolePower(rolePowerDTO);
        if (integer !=null && integer >0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRolePower(RolePowerDTO rolePowerDTO) {
        Integer integer = rolePowerMapper.deleteRolePower(rolePowerDTO);
        if (integer !=null && integer >0){
            return true;
        }
        return false;
    }
}
