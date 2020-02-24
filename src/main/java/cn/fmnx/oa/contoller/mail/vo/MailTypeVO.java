package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MailTypeVO
 * @Description: 邮件类型数据对象
 * @Author gmf
 * @Date 2020/2/24
 * @Version V1.0
 **/
@Data
@ApiModel(description = "邮件类型数据对象(通知，公告，邮件)")
public class MailTypeVO implements Serializable {
    private static final long serialVersionUID = 6103836769181642114L;
    @ApiModelProperty(name = "typeId",value = "类型id")
    private Long typeId;
    @ApiModelProperty(name = "typeName",value = "类型名字")
    private String typeName;
}
