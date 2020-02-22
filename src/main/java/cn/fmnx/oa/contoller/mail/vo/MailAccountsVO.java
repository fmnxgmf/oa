package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @ClassName MailAccountsVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/22
 * @Version V1.0
 **/
@Data
@ApiModel(description = "页面展示该用户的邮箱号")
public class MailAccountsVO implements Serializable {
    private static final long serialVersionUID = -8941599311899695375L;
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
    @ApiModelProperty(name = "mailUserName",value = "发件昵称,账号名")
    private String mailUserName;
    @ApiModelProperty(name = "mailCreateTime",value = "邮箱的创建时间")
    private String mailCreateTime;
}
