package cn.fmnx.oa.service.menuService.impl;

import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.contoller.menu.vo.MenuVO;
import cn.fmnx.oa.entity.user.User;
import cn.fmnx.oa.mapper.menuMapper.MenuMapper;
import cn.fmnx.oa.service.menuService.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Override
    public List<MenuVO> findAllSeeParentMenu(User user) {

        List<MenuVO> menuVOS = menuMapper.finaAllSeeParentMenu(user.getRoleId());
        return menuVOS;
    }

    @Override
    public List<MenuVO> findAllSeeSonMenu(Integer roleid, Integer parentId) {
        return menuMapper.finaAllSeeSonMenu(roleid,parentId);
    }
}
