package cn.fmnx.oa.entity.dept;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @ClassName Dept
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/5
 * @Version V1.0
 **/
@Table(name = "aoa_dept")
@Data
public class Dept {
    /**
     * //部门id
     */
    @Id
    @Column(name = "dept_id")
    @KeySql(useGeneratedKeys = true)
    private Long deptId;
    /**
     * //部门名字  非空 唯一
     */
    @Column(name = "dept_name")
    @NotEmpty(message="部门名称不为空")
    private String deptName;
    /**
     * //部门电话
     */
    @Column(name = "dept_tel")
    private String deptTel;
    /**
     * //部门传真
     */
    @Column(name = "dept_fax")
    private String deptFax;
    /**
     * //部门email
     */
    @Pattern(regexp = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$", message = "请填写正确邮箱号")
    private String email;
    /**
     * //部门地址
     */
    @Column(name = "dept_addr")
    private String deptAddr;

    private Long deptmanager;
    /**
     * //部门上班时间
     */
	@Column(name = "start_time")
	private Date startTime;
    /**
     * //部门下班时间
     */
    @Column(name = "end_time")
	private Date endTime;
}
