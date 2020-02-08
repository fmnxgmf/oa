package cn.fmnx.oa.entity.menu;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @ClassName Menu
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/5
 * @Version V1.0
 **/
@Data
@Table(name = "aoa_sys_menu")
public class Menu {

    @Id
    @Column(name = "menu_id")
    @KeySql(useGeneratedKeys = true)
    /**
     * // 菜单id
     */
    private Long menuId;
    /**
     * // 父id
     */
    @Column(name = "parent_id")
    private Long parentId=0L;
    /**
     * // 菜单名字
     */
    @Column(name = "menu_name")
    @NotEmpty(message="菜单名字不能为空")
    private String menuName;
    /**
     * // 菜单链接
     */
    @Column(name = "menu_url")
    @NotEmpty(message="{sysMenu.menuUrl.NotNull}")
    private String menuUrl;
    /**
     * // 菜单图标
     */
    @Column(name = "menu_icon")
    private String menuIcon;
    /**
     * // 菜单排序id
     */
    @Column(name = "sort_id")
    private Integer sortId=999;
    /**
     * // 菜单是否显示
     */
    @Column(name = "is_show")
    private Boolean show=false;
    /**
     * // 权限值分数
     */
    @Column(name = "menu_grade")
    private Integer menuGrade;
}
