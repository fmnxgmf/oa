package cn.fmnx.oa.entity.mail;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName Inmaillist
 * @Description: 内部邮件
 * @Author gmf
 * @Date 2020/2/19
 * @Version V1.0
 **/
@Table(name = "aoa_in_mail_list")
@Data
public class Inmaillist {
    /**
     * //邮件id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name="mail_id")
    private Long mailId;
    /**
     * //邮件类型（通知，公告，邮件）
     */
    @Column(name="mail_type")
    private Long mailType;
    /**
     * //邮件状态（一般，紧急，重要）
     */
    @Column(name="mail_status_id")
    private Long mailStatusid;
    /**
     * //发件人id
     */
   @Column(name = "mail_in_push_user_id")
    private Long mailUserid;
    /**
     * //邮件主题
     */
    @Column(name="mail_title")
    @NotBlank(message = "邮件主题不能为空")
    private String mailTitle;
    /**
     * //邮件内容
     */
    @Column(name="mail_content")
    @NotBlank(message="邮件内容不能为空")
    private String content;
    /**
     * //接收人（可以是多个）
     */
    @Column(name="in_receiver")
    private String inReceiver;
    /**
     * //邮件附件id
     */
    @Column(name = "mail_file_id")
    private Long mailFileid;
    /**
     * //邮件创建时间
     */
    @Column(name="mail_create_time")
    private Date mailCreateTime;
    /**
     * //外部邮件账号id
     */
    @Column(name = "mail_number_id")
    private Long mailNumberid;

    @Column(name="mail_del")
    private Integer del;

    @Column(name="mail_push")
    private Integer push;

    @Column(name="mail_star")
    private Integer star;
}
