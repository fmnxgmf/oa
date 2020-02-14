package cn.fmnx.oa.contoller.position.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PositionDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
@Data
@ApiModel(description = "添加或修改职位的数据对象")
public class PositionDTO implements Serializable {

    @ApiModelProperty(name = "positionId",value = "职位id,修改职位表需要id，添加职位不需要id，id自动生成",required = false)
    private Long positionId;
    @ApiModelProperty(value = "职位层级",name = "level")
    private Integer level;
    @ApiModelProperty(name = "name",value = "职位名字")
    private String name;
    @ApiModelProperty(name = "describtion",value = "该职位的描述信息")
    private String describtion;
    @ApiModelProperty(name = "deptId",value = "该职位对应的部门id")
    private Long deptId;
}
