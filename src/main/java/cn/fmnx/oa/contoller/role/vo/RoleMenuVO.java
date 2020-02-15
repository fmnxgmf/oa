package cn.fmnx.oa.contoller.role.vo;

import cn.fmnx.oa.contoller.menu.vo.ParentMenuVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RoleMenuVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@ApiModel(description = "该对象用于role权限设置")
@Data
public class RoleMenuVO implements Serializable {
    private static final long serialVersionUID = 2074535625436618818L;
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
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    private String menuUrl;

    @ApiModelProperty(value = "类型赋值为所有菜单")
    private String menuType = "菜单";
    @ApiModelProperty(value = "该菜单是否显示，1显示，0不显示",name = "isShow",example = "1")
    private Integer isShow;

}
