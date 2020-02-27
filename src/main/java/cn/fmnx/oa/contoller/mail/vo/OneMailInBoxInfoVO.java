package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName OneMailInBoxInfoVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/26
 * @Version V1.0
 **/
@Data
@ApiModel(description = "查看某一封收件箱邮件的详细信息")
public class OneMailInBoxInfoVO implements Serializable {
    private static final long serialVersionUID = 7581626471847228670L;
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
    /**
     * //邮件状态（一般，紧急，重要）
     */
    @ApiModelProperty(name = "mailStatusid",value = "邮件状态（20=一般，22=紧急，21=重要）")
    private Long mailStatusid;
    /**
     * //发件人id
     */
    @ApiModelProperty(name = "mailUserid",value = "发件人id")
    private Long mailUserid;
    @ApiModelProperty(name = "mailUserName",value = "发件人用户名")
    private String mailUserName;
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
