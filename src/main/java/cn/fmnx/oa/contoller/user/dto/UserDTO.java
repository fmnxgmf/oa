package cn.fmnx.oa.contoller.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
@ApiModel(description = "添加或修改用户对象的model")
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -1976833151316582641L;
    /**
     * 用户id
     */
    @ApiModelProperty(name = "userId",value = "用户id",example = "1")
    @NotNull(message = "id不能为空")
    private Long userId;
    /**
     * 登录用户名
     */
    @ApiModelProperty(name = "userName",value = "登录用户名",required = true)
    @NotBlank(message = "用户名不能为空")
    private String userName;
    /**
     * //真实姓名
     */
    @ApiModelProperty(name = "realName",value = "真实姓名",required = true)
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    /**
     * 用户电话
     */
    @ApiModelProperty(name = "userTel",value = "手机号码",required = true)
    @NotBlank(message = "电话不能为空")
    private String userTel;
    @ApiModelProperty(name = "pinyin",value = "姓名的汉字拼音")
    private String pinyin;
    /**
     *  //邮件
     */
    @ApiModelProperty(name = "eamil",value = "邮箱",required = true)
    @Email
    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$", message = "请填写正确邮箱号")
    private String eamil;
    /**
     * //地址
     */
    @ApiModelProperty(name = "address",value = "地址")
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * //用户学历
     */
    @ApiModelProperty(name = "userEdu",value = "学历",required = true)
    @NotEmpty(message = "学历不能为空")
    private String userEdu;

    /**
     * //学校
     */
    @ApiModelProperty(name = "userSchool",value = "毕业院校",required = true)
    @NotEmpty(message = "毕业院校不能为空")
    private String userSchool;

    /**
     * //用户身份证
     */
    @ApiModelProperty(name = "idCard",value = "用户身份证",required = true)
    @Pattern(regexp = "^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$", message = "请填写正确身份证号")
    private String idCard;
    /**
     *  //银行
     */
    @ApiModelProperty(name = "bank",value = "银行卡号",required = true)
    @NotEmpty(message = "卡号不能为空")
    @Length(min = 16, max = 19, message = "银行卡号长度必须在16到19之间!")
    private String bank;
    /**
     * //性别
     */
    @ApiModelProperty(name = "sex",value = "性别",example = "女")
    private String sex;

    /**
     * //主题皮肤
     */
    @ApiModelProperty(name = "themeSkin",value = "皮肤")
    private String themeSkin;
    /**
     * //生日
     */
    @ApiModelProperty(name = "birth",value = "生日")
    private Date birth;
    /**
     * //用户签名
     */
    @ApiModelProperty(name = "userSign",value = "用户签名")
    private String userSign;
    /**
     * //用户密码
     */
    @ApiModelProperty(name = "password",value = "用户密码",required = true)
    private String password;
    /**
     * //用户薪水
     */
    @ApiModelProperty(name = "salary",value = "用户薪水",required = true,example = "1000.0")
    private float salary;
    /**
     * //用户头像路径
     */
    @ApiModelProperty(name = "imgPath",value = "用户头像路径,先上传图片然后返回图片路径，最后将图片路径赋值给imgPath字段一起提交到后端")
    private String imgPath;

    /**
     *  //入职时间
     */
    @ApiModelProperty(name = "hireTime",value = "入职时间")
    private Date hireTime;
    /**
     * //外键关联 职位表
     */
    @ApiModelProperty(name = "positionId",value = "职位id",example = "1")
    private Long positionId;

    /**
     * //外键关联 部门表
     */
    @ApiModelProperty(name = "deptId",value = "部门id",example = "1")
    private Long deptId;

    /**
     * //外键关联 角色表
     */
    @ApiModelProperty(name = "roleId",value = "角色id",example = "1")
    private Long roleId;
}
