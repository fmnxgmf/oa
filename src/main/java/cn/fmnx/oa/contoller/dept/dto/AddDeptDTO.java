package cn.fmnx.oa.contoller.dept.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$")
    private String deptTel;
    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email",value = "邮箱")
    @Pattern(regexp = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$", message = "请填写正确邮箱号")
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
    @Pattern(regexp = "^(\\d{3,4}-)?\\d{7,8}$")
    private String deptFax;
}
