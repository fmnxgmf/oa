package cn.fmnx.oa.contoller.role.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName RoleVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@Data
@ApiModel(description = "展示角色数据的对象")
public class RoleVO {
    /**
     * //角色id
     */
    @ApiModelProperty(name = "roleId",value = "角色id",example = "1")
    private Long roleId;
    /**
     * //角色名
     */
    @ApiModelProperty(name = "roleName",value = "角色名字")
    private String roleName;
    /**
     * 权限值
     */
    @ApiModelProperty(name = "roleValue",value = "角色权限值",example = "1")
    private Integer  roleValue;
}
