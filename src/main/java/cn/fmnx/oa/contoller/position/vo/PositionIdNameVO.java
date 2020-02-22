package cn.fmnx.oa.contoller.position.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PositionIdNameVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@Data
@ApiModel(description = "展示职位id和名称，一般用于下拉框")
public class PositionIdNameVO implements Serializable {

    private static final long serialVersionUID = -1843802187886773258L;
    @ApiModelProperty(name = "positionId",value = "部门id",example = "1")
    private Long positionId;
    @ApiModelProperty(name = "name",value = "部门名称")
    private String name;
}
