package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MailStatusVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/24
 * @Version V1.0
 **/
@Data
@ApiModel(description = "邮件状态（一般，紧急，重要）")
public class MailStatusVO implements Serializable {
    private static final long serialVersionUID = -493089624647266036L;
    @ApiModelProperty(name = "statusId",value = "邮件状态id")
    private Long statusId;
    @ApiModelProperty(name = "statusName",value = "邮件状态名")
    private String statusName;
}
