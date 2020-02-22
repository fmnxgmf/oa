package cn.fmnx.oa.entity.user;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName UserLoginRecord
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/15
 * @Version V1.0
 **/
@Data
@Table(name = "aoa_user_login_record")
public class UserLoginRecord {
    /**
     * record_id
     * browser
     * ip_addr
     * login_time
     * session_id
     * user_id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "record_id")
    private Long recordId;
    @Column(name = "browser")
    private String browser;
    @Column(name = "ip_addr")
    private String ipAddr;
    @Column(name = "login_time")
    private Date loginTime;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "user_id  ")
    private Long userId;
}
