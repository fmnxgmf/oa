package cn.fmnx.oa.contoller.dept.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AddDeptDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/12
 * @Version V1.0
 **/
@Data
@ApiModel(description = "添加或修改部门管理的类")
public class AddDeptDTO implements Serializable {
    private static final long serialVersionUID = -4905033211564563272L;
    /**
     * 部门id
     */
    @ApiModelProperty(name = "deptId",value = "部门id，修改必须传id值，添加不用传id")
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
    /**
     * 部门传真
     */
    @ApiModelProperty(name = "deptFax",value = "传真")
    private String deptFax;
}
