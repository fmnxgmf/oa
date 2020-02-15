package cn.fmnx.oa.contoller.menu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AddMenuDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/11
 * @Version V1.0
 **/
@Data
@ApiModel(description = "添加菜单，修改菜单的model对象")
public class AddMenuDTO {
    /**
     * 菜单id
     */
    @ApiModelProperty(value = "菜单id")
    private Long menuId;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称",required = true)
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
     * 父级菜单
     */
    @ApiModelProperty(value = "给后端专递许id值，不能是name值")
    private Long parentId;
    /**
     *排序
     */
    @ApiModelProperty(value = "排序值",name = "sortId",example = "2")
    private Integer sortId;
    /**
     * 菜单是否显示 1 显示
     */
    @ApiModelProperty(value = "菜单是否显示 1为显示",name = "isShow",dataType = "int",example = "1")
    private Integer isShow;
}
