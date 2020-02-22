package cn.fmnx.oa.contoller.role.dto;

import cn.fmnx.oa.entity.menu.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName RolePowerDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/15
 * @Version V1.0
 **/
@Data
@ApiModel(description = "保存设置完权限的数据对象，角色权限的添加与pkId无关，与角色id和菜单id相关")
public class RolePowerDTO {
    @ApiModelProperty(name = "roleId",value = "角色id",example = "1")
    private Long roleId;
    @ApiModelProperty(name = "menuId",value = "菜单id",example = "1")
    private Long menuId;
    @ApiModelProperty(name = "isShow",value = "菜单表中菜单是否展示",example = "1")
    private Integer isShow;
}
