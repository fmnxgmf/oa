package cn.fmnx.oa.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * @ClassName User
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/5
 * @Version V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aoa_user")
public class User {


    @Id
    @KeySql(useGeneratedKeys = true)
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 登录用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "电话不能为空")
    /**
     * 用户电话
     */
    private String userTel;
    /**
     * //真实姓名
     */
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    private String pinyin;
    /**
     *  //邮件
     */
    @Email
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$", message = "请填写正确邮箱号")
    private String eamil;
    /**
     * //地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * //用户学历
     */
    @NotEmpty(message = "学历不能为空")
    private String userEdu;


    private Boolean superman = false;

    /**
     * //学校
     */
    @NotEmpty(message = "毕业院校不能为空")
    private String userSchool;

    /**
     * //用户身份证
     */
    @Column(name = "user_idcard")
    @Pattern(regexp = "^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$", message = "请填写正确身份证号")
    private String idCard;
    /**
     *  //银行
     */
    @NotEmpty(message = "卡号不能为空")
    @Length(min = 16, max = 19, message = "银行卡号长度必须在16到19之间!")
    private String bank;
    /**
     * //性别
     */
    private String sex;

    /**
     * //主题皮肤
     */
    private String themeSkin;
    /**
     * //生日
     */
    private Date birth;
    /**
     * //用户签名
     */
    private String userSign;
    /**
     * //用户密码
     */
    private String password;
    /**
     * //用户薪水
     */
    private String salary;
    /**
     * //用户头像路径
     */
    private String imgPath;

    /**
     *  //入职时间
     */
    private Date hireTime;

    /**
     * //该用户是否被禁用
     */
    private Integer isLock = 0;

    /**
     * //用户最后登录ip；
     */
    private String lastLoginIp;

    /**
     * //最后登录时间
     */
    private Date lastLoginTime;

    /**
     * //最后修改时间
     */
    private Date modifyTime;

    /**
     * //最后修改此用户的用户id
     */
    private Long modifyUserId;

    /**
     * //上司id
     */
    private Long fatherId;
    /**
     * //请假天数
     */
    private Integer holiday;

    private Integer positionId;	//外键关联 职位表


    private Integer deptId;			//外键关联 部门表


    private Integer roleId;			//外键关联 角色表

    public User(Long userId, @NotBlank(message = "用户名不能为空") String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
