package cn.fmnx.oa.entity.mail;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @ClassName Mailnumber
 * @Description: 邮箱账号表
 * @Author gmf
 * @Date 2020/2/19
 * @Version V1.0
 **/
@Table(name="aoa_mailnumber")
@Data
public class Mailnumber {
    /**
     * //邮箱的主键id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name="mail_number_id")
    private Long mailNumberId;
    /**
     * //邮件账号类型
     */
    @Column(name="mail_type")
    private Long mailType;
    /**
     * //邮件状态（是否可用）
     */
    @Column(name="status")
    private Long status;
    /**
     * //用户id
     */
    @Column(name="mail_num_user_id")
    private Long mailUserId;
    /**
     * //用户别名
     */
    @Column(name="mail_user_name",nullable=false)
    @NotBlank(message="发件昵称不能为空")
    private String mailUserName;
    /**
     * //账号创建时间
     */
    @Column(name="mail_create_time")
    private Date mailCreateTime;
    /**
     * //邮件账号
     */
    @Column(name="mail_account",nullable=false)
    @NotBlank(message="邮件账号不能为空")
    @Pattern(regexp="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$",message="请填写正确邮箱号")
    private String mailAccount;
    /**
     * //账号授权码0111111111111111
     */
    @Column(name="password",nullable=false)
    @NotBlank(message="授权码不能为空")
    private String password;
    /**
     * //账号信息备注
     */
    @Column(name="mail_des")
    private String mailDes;
}
