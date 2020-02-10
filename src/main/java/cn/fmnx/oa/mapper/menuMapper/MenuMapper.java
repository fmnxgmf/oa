package cn.fmnx.oa.mapper.menuMapper;

import cn.fmnx.oa.contoller.menu.vo.MenuVO;
import cn.fmnx.oa.entity.menu.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {
    /**
     * @MethodName: finaAllSeeParentMenu
     * @Description: 查找所有可以显示的父级菜单，parent_id = 0
     * @Param: []
     * @Return: java.util.List<cn.fmnx.oa.entity.menu.vo.MenuVO>
     * @Author: gmf
     * @Date: 2020/2/10
    **/
    @Select("select " +
            "menu.menu_id as menuId,"+
            "menu.is_show as isShow," +
            "menu.menu_name as menuName, " +
            "menu.menu_icon as menuIcon, " +
            "menu.menu_url as menuUrl," +
            "case when menu.parent_id = '0 'then '父级菜单' else '子级菜单'end  as menuType ,"+
            "menu.sort_id as sortId  "+
            "from aoa_sys_menu as menu ,aoa_role_power_list as role " +
            "where menu.parent_id = 0 and menu.is_show = 1 and role.role_id = #{roleId} and role.menu_id = menu.menu_id order by menu.sort_id asc")
    List<MenuVO> finaAllSeeParentMenu(@Param("roleId")Integer roleId);
   /**
    * @MethodName: finaAllSeeSonMenu
    * @Description: 查找所有可以显示的父级菜单，parent_id != 0
    * @Param: [roleId, parentId]
    * @Return: java.util.List<cn.fmnx.oa.contoller.menu.vo.MenuVO>
    * @Author: gmf
    * @Date: 2020/2/10
   **/
    List<MenuVO> finaAllSeeSonMenu(@Param("roleId") Integer roleId,@Param("parentId") Integer parentId);
}
