package cn.fmnx.oa.service.menuService;

import cn.fmnx.oa.contoller.menu.vo.MenuVO;
import cn.fmnx.oa.entity.user.User;

import java.util.List;

public interface MenuService {
    List<MenuVO> findAllSeeParentMenu(User user);

    List<MenuVO> findAllSeeSonMenu(Integer roleid, Integer parentId);
}
