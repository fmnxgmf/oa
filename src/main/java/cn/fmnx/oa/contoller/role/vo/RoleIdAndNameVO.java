package cn.fmnx.oa.contoller.role.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName RoleIdAndNameVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@Data
@ApiModel(description = "展示角色id和角色名称")
public class RoleIdAndNameVO {
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

}
