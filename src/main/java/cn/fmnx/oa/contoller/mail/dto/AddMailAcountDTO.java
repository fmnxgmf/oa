package cn.fmnx.oa.contoller.mail.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName AddMailAcountDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/19
 * @Version V1.0
 **/
@Data
@ApiModel(description = "添加邮箱账户所需的数据对象")
public class AddMailAcountDTO implements Serializable {
    private static final long serialVersionUID = 1006139184876026593L;
    @ApiModelProperty(name = "mailNumberId",value = "邮箱的id")
    private Long mailNumberId;
    /**
     * 邮件类型
     */
    @ApiModelProperty(name = "mailType",value = "邮件类型(1=系统邮件；2=公司邮件)")
    private Long mailType;
    /**
     * 邮件状态
     */
    @ApiModelProperty(name = "status",value = "邮件状态(1=有效，2=w无效)")
    private Long status;
    /**
     * 发件昵称
     */
    @NotBlank(message = "发件昵称不能为空")
    @ApiModelProperty(name = "mailUserName",value = "发件昵称")
    private String mailUserName;
    /**
     * 邮箱账户
     */
    @ApiModelProperty(name = "mailAccount",value = "邮箱账户",required = true)
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$", message = "请填写正确邮箱号")
    private String mailAccount;
    /**
     * 邮箱授权码，用于向外部邮件发送
     */
    @NotBlank(message = "邮箱授权码不能为空")
    @ApiModelProperty(name = "password",value = "邮箱授权码")
    private String password;
    /**
     * 备注信息
     */
    @ApiModelProperty(name = "mailDes",value = "备注信息")
    private String mailDes;
    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty(name = "mailUserId",value = "即该用户的userId,前端页面展示的为用户名")
    private Long mailUserId;
    /**
     * 邮箱创建时间
     */
    @ApiModelProperty(name = "mailCreateTime",value = "邮箱创建时间前端不管，后端来维护",required = false)
    private Date mailCreateTime;
}
