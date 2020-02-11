package cn.fmnx.oa.contoller.menu.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AddTypeListDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/
@ApiModel(description = "接收类型管理添加和修改数据的Model对象")
@Data
public class AddTypeListDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "类型id值",name = "typeId",required = false,example = "49")
    private Long TypeId;
    /**
     * 模块
     */
    @ApiModelProperty(value = "所属模块",name = "typeModel",required = true,example = "aoa_bursement")
    private String typeModel;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型名称",name = "typeName",required = true,example = "病假")
    private String typeName;
    /**
     * 排序值
     */
    @ApiModelProperty(value = "排序值",name = "typeSortValue",required = true,example = "1")
    private Integer typeSortValue;
    /**
     * 类型颜色
     */
    @ApiModelProperty(name = "类型颜色",value = "typeColor",required = true,example = "red")
    private String typeColor;

}
