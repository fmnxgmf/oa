package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DraftsBoxVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/27
 * @Version V1.0
 **/
@Data
@ApiModel(description = "草稿箱的数据对象")
public class DraftsBoxVO implements Serializable {

    private static final long serialVersionUID = 6276865536505205050L;
    /**
     * //邮件id
     */
    @ApiModelProperty(name = "mailId",value = "邮件id",required = false)
    private Long mailId;
    /**
     * //邮件类型（通知，公告，邮件）
     */
    @ApiModelProperty(name = "mailType",value = "邮件类型（17=通知，18=公告，16=邮件）")
    private Long mailType;
    @ApiModelProperty(name = "inReceiver",value = "收件人")
    private String   inReceiverName;
    @ApiModelProperty(name = "inReceiverId",value = "收件人id")
    private String   inReceiverId;
    /**
     * //邮件状态（一般，紧急，重要）
     */
    @ApiModelProperty(name = "mailStatusid",value = "邮件状态（20=一般，22=紧急，21=重要）")
    private Long mailStatusid;
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
     * //邮件附件id
     */
    @ApiModelProperty(name = "mailFileid",value = "邮件附件id")
    private Long mailFileid;
    /**
     * 邮件附件的链接
     */
    @ApiModelProperty(name = "path",value = "附件的链接值")
    private String path;
    /**
     * //邮件创建时间
     */
    @ApiModelProperty(name = "mailCreateTime",value = "邮件创建时间")
    private String mailCreateTime;

}
