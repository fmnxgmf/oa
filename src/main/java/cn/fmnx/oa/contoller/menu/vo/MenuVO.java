package cn.fmnx.oa.contoller.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MenuVO
 * @Description: 菜单显示页面数据
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/

/**
 * menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `is_show` int(11) DEFAULT NULL,
 *   `menu_grade` int(11) DEFAULT NULL,
 *   `menu_icon` varchar(255) DEFAULT NULL,
 *   `menu_name` varchar(255) DEFAULT NULL,
 *   `menu_url` varchar(255) NOT NULL,
 *   `parent_id` bigint(20) DEFAULT NULL,
 *   `sort_id` int(11) DEFAULT NULL,
 */
@Data
@ApiModel(description = "返回菜单栏首页数据给前端的对象")
public class MenuVO implements Serializable {

    private static final long serialVersionUID = -5009141333570070036L;
    /**
     * 菜单id
     */
    @ApiModelProperty(value = "菜单id")
    private Long menuId;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;
    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    private String menuUrl;
    /**
     * 菜单类型[父级菜单，子级菜单]
     */
    @ApiModelProperty(value = "菜单类型[父级菜单，子级菜单]")
    private String menuType;
    /**
     *排序
     */
    @ApiModelProperty(value = "排序值")
    private Integer sortId;
    /**
     * 菜单是否显示 1 显示
     */
    @ApiModelProperty(value = "菜单是否显示 1为显示")
    private Integer isShow;

    private ParentMenuVO parentMenuVO;
}
