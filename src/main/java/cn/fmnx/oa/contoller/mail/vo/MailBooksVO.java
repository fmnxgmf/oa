package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MailBooksVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/24
 * @Version V1.0
 **/
@ApiModel(description = "内部邮箱发送时通讯录对象,邮件接收者用userId传递")
@Data
public class MailBooksVO implements Serializable {
    private static final long serialVersionUID = -1929151764943778492L;
    @ApiModelProperty(name = "userId",value = "用户id")
    private Long userId;
    @ApiModelProperty(name = "userName",value = "用户名")
    private String userName;
    @ApiModelProperty(name = "email",value = "该用户的邮箱")
    private String eamil;
}
