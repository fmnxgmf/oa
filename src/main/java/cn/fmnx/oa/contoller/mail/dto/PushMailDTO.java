package cn.fmnx.oa.contoller.mail.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName PushMail
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/23
 * @Version V1.0
 **/
@Data
@ApiModel(description = "发送邮件所需要的数据对象")
public class PushMailDTO implements Serializable {

    /**
     * //邮件类型（通知，公告，邮件）
     */
    @ApiModelProperty(name = "mailType",value = "邮件类型（17=通知，18=公告，16=邮件）",example = "17")
    private Long mailType;
    /**
     * //邮件状态（一般，紧急，重要）
     */
    @ApiModelProperty(name = "mailStatusid",value = "邮件状态（20=一般，22=紧急，21=重要）",example = "20")
    private Long mailStatusid;
    /**
     * //发件人id
     */
    @ApiModelProperty(name = "mailUserid",value = "发件人id")
    private Long mailUserid;
    /**
     * //邮件主题
     */
    @ApiModelProperty(name = "mailTitle",value = "邮件主题")
    private String mailTitle;
    /**
     * //邮件内容
     */
    @ApiModelProperty(name = "content",value = "邮件内容")
    private String content;
    /**
     * //接收人（可以是多个）
     */
    @ApiModelProperty(name = "inReceiver",value = "接收人是（可以是多个）userId，以‘;’l来分个接收人",example = "1;2")
    private String inReceiver;
    /**
     * //邮件附件id
     */
    @ApiModelProperty(name = "mailFileid",value = "邮件附件id")
    private Long mailFileid;
    /**
     * //邮件创建时间
     */
    @ApiModelProperty(name = "mailCreateTime",value = "邮件创建时间,前端不用提交，后端自己维护",required = false)
    private Date mailCreateTime;
    /**
     * //外部邮件账号id
     */
    @ApiModelProperty(name = "mailNumberid",value = "外部邮件账号id,内部邮件发送可以不用，外部邮件发送需要")
    private Long mailNumberid;
    /**
     * 邮件是否删除
     */
    @ApiModelProperty(name = "del",value = "邮件是否删除(0=未删除)，后端自己维护",required = false)
    private Integer del;
    /**
     * 邮件是否发送成功
     */
    @ApiModelProperty(name = "push",value = "邮件是否发送成功(0=发送成功)，后端自己维护",required = false)
    private Integer push;
    /**
     * 邮件是否标星
     */
    @ApiModelProperty(name = "star",value = "邮件是否标星(0=未标星)，后端自己维护",required = false)
    private Integer star;
}
