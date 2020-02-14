package cn.fmnx.oa.contoller.role.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName RoleDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@ApiModel(description = "添加或修改角色的数据对象")
@Data
public class RoleDTO {
    /**
     * //角色id
     */
    @ApiModelProperty(name = "roleId",value = "角色id")
    private Long roleId;
    /**
     * //角色名
     */
    @ApiModelProperty(name = "roleName",value = "角色名字")
    private String roleName;
    /**
     * 权限值
     */
    @ApiModelProperty(name = "roleValue",value = "角色权限值")
    private Integer  roleValue;
}
