package cn.fmnx.oa.contoller.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName StatusListVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@Data
@ApiModel(description = "状态管理对象页面所展示的数据")
public class StatusListVO {
    @ApiModelProperty(name = "statusId",value = "状态id，添加时不用添加id，修改时需要添加id到后端",example = "1")
    private Long statusId;
    @ApiModelProperty(name = "statusModel",value = "模块")
    private String statusModel;
    @ApiModelProperty(name = "statusName",value = "状态名字")
    private String statusName;
    @ApiModelProperty(name = "statusSortValue",value = "状态排序值",example = "1")
    private Integer statusSortValue;
    @ApiModelProperty(name = "statusColor",value = "状态颜色")
    private String statusColor;
}
