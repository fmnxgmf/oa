package cn.fmnx.oa.contoller.dept.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DeptVO
 * @Description: 部门管理展示的数据对象
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@Data
@ApiModel(description = "部门管理展示的数据对象")
public class DeptVO {
    /**
     * 部门id
     */
    @ApiModelProperty(name = "deptId",value = "部门id",example = "1")
    private Long deptId;
    /**
     * 名称
     */
    @ApiModelProperty(name = "deptName",value = "名称")
    private String deptName;
    /**
     * 电话
     */
    @ApiModelProperty(name = "deptTel",value = "电话")
    private String deptTel;
    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email",value = "邮箱")
    private String email;
    /**
     * 地址
     */
    @ApiModelProperty(name = "deptAddr",value = "地址")
    private String deptAddr;
}

