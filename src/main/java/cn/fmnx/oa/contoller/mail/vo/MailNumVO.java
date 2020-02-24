package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MailNumVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/24
 * @Version V1.0
 **/
@Data
@ApiModel(description = "职员外部邮箱账号")
public class MailNumVO implements Serializable {
    private static final long serialVersionUID = -3559051305634446058L;
    @ApiModelProperty(name = "mailNumberId",value = "外部邮箱的id")
    private Long mailNumberId;
    @ApiModelProperty(name = "mailUserName",value = "外部邮箱的用户别名")
    private String mailUserName;
    @ApiModelProperty(name = "mailAccount",value = "外部邮箱的邮件账户")
    private String mailAccount;
}
