package cn.fmnx.oa.entity.mail;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

/**
 * @ClassName Mailreciver
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/19
 * @Version V1.0
 **/
@Data
@Table(name="aoa_mail_reciver")
public class Mailreciver {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name="pk_id")
    private Long pkId;

    /**
     * //内部邮件id
     */

    @Column(name="mail_id")
    private Inmaillist mailId;


    /**
     * //内部用户id
     */
    @Column(name="mail_reciver_id")
    private Long reciverId;
    /**
     * //是否已读
     */
    @Column(name="is_read")
    private Boolean read=false;
    /**
     * //是否星标
     */
    @Column(name="is_star")
    private Boolean star=false;
    /**
     * //是否真正删除
     */
    @Column(name="is_del")
    private Boolean del=false;
}
