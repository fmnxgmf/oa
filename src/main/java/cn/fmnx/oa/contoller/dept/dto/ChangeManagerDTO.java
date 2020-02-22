package cn.fmnx.oa.contoller.dept.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ChangeManagerVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/16
 * @Version V1.0
 **/
@Data
@ApiModel(description = "经理级别的人事变动所需要的数据")
public class ChangeManagerDTO implements Serializable {
    private static final long serialVersionUID = 4096183231422400281L;
    @ApiModelProperty(name = "userId",value = "当前经理的userId")
    private Long userId;
    @ApiModelProperty(name = "deptId",value = "当前经理准备去的部门id")
    private Long deptId;
    @ApiModelProperty(name = "positionId",value = "当前经理去的部门的职位id")
    private Long positionId;
    @ApiModelProperty(name = "newUserId",value = "该部门新任经理的userId")
    private Long newUserId;
    @ApiModelProperty(name = "oldDeptId",value = "该部门的id")
    private Long oldDeptId;
    @ApiModelProperty(name = "oldRoleId",value ="新任经理的角色id就是原经理的角色id" )
    private Long oldRoleId;
    @ApiModelProperty(name = "oldPositionId",value = "新任经理的职位id就是原经理的职位id")
    private Long oldPositionId;


}
