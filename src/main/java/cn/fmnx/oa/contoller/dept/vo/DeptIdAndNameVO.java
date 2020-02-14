package cn.fmnx.oa.contoller.dept.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DeptIdAndNameVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/14
 * @Version V1.0
 **/
@Data
@ApiModel(description = "本来只展示部门的id和名称，一般用于下拉框的值")
public class DeptIdAndNameVO implements Serializable {

    private static final long serialVersionUID = 7831293899836323411L;
    @ApiModelProperty(name = "deptId",value = "部门的id",notes = "此id用于前后端转递")
    private Long deptId;
    @ApiModelProperty(name = "deptName",value = "部门名称",notes = "前端展示数据有部门名称，后端需要id")
    private String deptName;
}
