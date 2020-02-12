package cn.fmnx.oa.service.menuService.impl;

import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.contoller.menu.vo.MenuVO;
import cn.fmnx.oa.contoller.menu.vo.ParentMenuVO;
import cn.fmnx.oa.entity.menu.Menu;
import cn.fmnx.oa.entity.user.User;
import cn.fmnx.oa.mapper.menuMapper.MenuMapper;
import cn.fmnx.oa.service.menuService.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
@Transactional
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

    @Override
    public List<MenuVO> findAllMenus() {
        List<MenuVO> allMenus = menuMapper.findAllMenus();
        return allMenus;
    }

    @Override
    public boolean addMenu(Menu menu) {
        int insert = menuMapper.insert(menu);
        if (insert > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<ParentMenuVO> findParent() {
        return menuMapper.findParenMenus();

    }

    @Override
    public MenuVO findOneMenuById(Long menuId) {
        return menuMapper.findOneMenuByid(menuId);

    }

    @Override
    public boolean updateMenu(Menu menu) {
        int i = menuMapper.updateByPrimaryKey(menu);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMenu(Long menuId) {
        int i = menuMapper.deleteByPrimaryKey(menuId);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean changeSortId(Long parentId, Integer sortId, Long menuId, Integer step) {
        //1.要先修改他人的排序值，再修改自己的排序值
       int other = 0;
       int oneSelf = 0;
        //修改其它的排序值
        other = menuMapper.chageOtherSortId(parentId,sortId,menuId,step);
        if(other > 0){
            //修改自己的排序值
            oneSelf= menuMapper.changeOneSelfSortId(parentId,sortId,menuId,step);
        }
        if(oneSelf > 0 && other > 0){
            return true;
        }else {
            //手动事务强制回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

    }

    @Override
    public List<MenuVO> findMenuByLikeName(String menuName) {
        return menuMapper.findMenuByLikeName(menuName);
    }
}
