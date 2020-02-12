package cn.fmnx.oa.service.menuService;

import cn.fmnx.oa.contoller.menu.vo.MenuVO;
import cn.fmnx.oa.contoller.menu.vo.ParentMenuVO;
import cn.fmnx.oa.entity.menu.Menu;
import cn.fmnx.oa.entity.user.User;

import java.util.List;

/**
 * @author gmf
 */
public interface MenuService {
    List<MenuVO> findAllSeeParentMenu(User user);

    List<MenuVO> findAllSeeSonMenu(Integer roleid, Integer parentId);

    List<MenuVO> findAllMenus();

    boolean addMenu(Menu menu);

    List<ParentMenuVO> findParent();

    MenuVO findOneMenuById(Long menuId);

    boolean updateMenu(Menu menu);

    boolean deleteMenu(Long menuId);

    boolean changeSortId(Long parentId, Integer sortId, Long menuId, Integer step);

    List<MenuVO> findMenuByLikeName(String menuName);
}
