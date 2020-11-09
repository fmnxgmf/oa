package cn.fmnx.oa.entity.attends;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName Attends
 * @Description: 考勤表
 * @Author gmf
 * @Date 2020/3/1
 * @Version V1.0
 **/
@Table(name="aoa_attends_list")
@Data
public class Attends {
    @Id
    @Column(name="attends_id")
    @KeySql(useGeneratedKeys = true)
    private Long attendsId;
    /**
     * //类型id
     */
    @Column(name="type_id")
    private Long typeId;
    /**
     * //状态id
     */
    @Column(name="status_id")
    private Long statusId;
    /**
     * //考勤时间
     */
    @Column(name="attends_time")
    private Date attendsTime;
    /**
     * //考勤时分
     */
    @Column(name="attend_hmtime")
    private String attendHmtime;
    /**
     * //星期几
     */
    @Column(name="week_ofday")
    private String weekOfday;
    /**
     * //登陆ip
     */
    @Column(name="attends_ip")
    private String attendsIp;
    /**
     * //考勤备注
     */
    @Column(name="attends_remark")
    private String attendsRemark;
    /**
     * //请假开始时间
     */
    @Column(name="holiday_start")
    private Date holidayStart;
    /**
     * //请假时间（天数）
     */
    @Column(name="holiday_days")
    private Double holidayDays;
    /**
     * 用户id
     */
    @Column(name = "attends_user_id")
    private Long attendsUserId;
}
