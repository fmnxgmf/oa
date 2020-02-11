package cn.fmnx.oa.contoller.menu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TypeListVO
 * @Description: 类型管理页面所需要展示的数据
 * @Author gmf
 * @Date 2020/2/10
 * @Version V1.0
 **/
@Data
@ApiModel(description = "返回给前端类型管理页面数据的对象")
public class TypeListVO implements Serializable {

    private static final long serialVersionUID = 1833046042386236165L;
    /**
     * id
     */
    @ApiModelProperty(value = "类型id",name = "typeId")
    private Long typeId;
    /**
     * 模块
     */
    @ApiModelProperty(value = "所属模块",name = "typeModel")
    private String typeModel;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型名称",name = "typeName")
    private String typeName;
    /**
     * 排序值
     */
    @ApiModelProperty(value = "排序值",name = "typeSortValue")
    private Integer typeSortValue;

}
