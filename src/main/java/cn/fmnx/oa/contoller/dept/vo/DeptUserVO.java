package cn.fmnx.oa.contoller.dept.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DeptUserVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/16
 * @Version V1.0
 **/
@Data
@ApiModel(description = "部门员工信息展示")
public class DeptUserVO {
    @ApiModelProperty(name = "roleId",value = "角色id当该用户时经理时才返回roleId")
    private Long roleId;
    @ApiModelProperty(name = "userId",value = "员工id")
    private Long userId;
    @ApiModelProperty(name = "imgPath",value = "该员工头像")
    private String imgPath;
    @ApiModelProperty(name = "userName",value = "该员工的用户名")
    private String userName;
    @ApiModelProperty(name = "realName",value = "该员工真实姓名")
    private String realName;
    @ApiModelProperty(name = "positionName",value = "该员工职位名称")
    private String positionName;
}
