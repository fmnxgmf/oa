package cn.fmnx.oa.service.roleService;

import cn.fmnx.oa.contoller.role.vo.RoleIdAndNameVO;
import cn.fmnx.oa.mapper.roleMapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class RoleServiceImpl implements RoleService{
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<RoleIdAndNameVO> findAllRoleIdName() {
        List<RoleIdAndNameVO> roleIdAndNameVOS = roleMapper.findAllRoleIdName();
        return roleIdAndNameVOS;
    }
}
