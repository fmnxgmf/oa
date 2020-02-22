package cn.fmnx.oa.contoller.menu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AddStatusDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@Data
@ApiModel(description = "状态管理添加或修改的对象数据")
public class AddStatusDTO implements Serializable {

    private static final long serialVersionUID = -7891635722963740374L;
    @ApiModelProperty(name = "statusId",value = "状态id，添加时不用添加id，修改时需要添加id到后端",required = false,example = "1")
    private Long statusId;
    @ApiModelProperty(name = "statusModel",value = "模块",required = true,example = "aoa_mailnumber")
    private String statusModel;
    @ApiModelProperty(name = "statusName",value = "状态名字",required = true,example = "有效")
    private String statusName;
    @ApiModelProperty(name = "statusSortValue",value = "状态排序值",required = true,example = "2")
    private Integer statusSortValue;
    @ApiModelProperty(name = "statusColor",value = "状态颜色",required = true,example = "red")
    private String statusColor;
}
