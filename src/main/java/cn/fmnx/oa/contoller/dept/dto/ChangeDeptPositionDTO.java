package cn.fmnx.oa.contoller.dept.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ChangeDeptPositionDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/16
 * @Version V1.0
 **/
@Data
@ApiModel(description = "人事变动所需的数据")
public class ChangeDeptPositionDTO implements Serializable {

    private static final long serialVersionUID = -1389262640807599960L;
    @ApiModelProperty(name = "userId",value = "用户id")
    private Long userId;
    @ApiModelProperty(name = "deptId",value = "部门id")
    private Long deptId;
    @ApiModelProperty(name = "positionId",value = "职位id")
    private Long positionId;
}
