package cn.fmnx.oa.common.mail;

import lombok.Data;

import java.io.File;
import java.io.Serializable;

/**
 * @ClassName JmsDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/29
 * @Version V1.0
 **/
@Data
public class JmsDTO implements Serializable {
    private static final long serialVersionUID = -3481535946948475919L;
    /**
     * 邮件服务器
     */
    private String host;
    /**
     * 邮件发送者
     */
    private String username;
    /**
     * 邮箱的授权码
     */
    private String password;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件的接收者
     */
    private String to;
    /**
     * 邮件的内容
     */
    private String text;
    /**
     * 邮件附件路径
     */
    private String filePath;
    /**
     * 邮件附件名字
     */
    private String attachmentName;
}
