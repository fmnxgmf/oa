package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ReplyMailInboxVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/26
 * @Version V1.0
 **/
@Data
@ApiModel(description = "回复邮件回显的数据")
public class ReplyMailInboxVO implements Serializable {
    private static final long serialVersionUID = 7214654850940388613L;
    /**
     * //邮件id
     */
    @ApiModelProperty(name = "mailId",value = "原邮件id",required = false)
    private Long mailId;
    /**
     * //邮件类型（通知，公告，邮件）
     */
    @ApiModelProperty(name = "mailType",value = "邮件类型（17=通知，18=公告，16=邮件）")
    private Long mailType;
    /**
     * //邮件状态（一般，紧急，重要）
     */
    @ApiModelProperty(name = "mailStatusid",value = "邮件状态（20=一般，22=紧急，21=重要）")
    private Long mailStatusid;
    /**
     * //发件人id
     */
    @ApiModelProperty(name = "mailUserid",value = "原发件人id")
    private Long mailUserid;
    @ApiModelProperty(name = "mailUserName",value = "原发件人用户名，就是回复邮件的收件人，该字段前端不能更改，后端回将该字段的id，name都返回")
    private String mailUserName;
    /**
     * //邮件主题
     */
    @ApiModelProperty(name = "mailTitle",value = "原邮件主题")
    private String mailTitle;
    /**
     * //邮件创建时间
     */
    @ApiModelProperty(name = "mailCreateTime",value = "原邮件创建时间")
    private String mailCreateTime;
}
