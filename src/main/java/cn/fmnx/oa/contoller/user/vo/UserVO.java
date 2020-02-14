package cn.fmnx.oa.contoller.user.vo;

import cn.fmnx.oa.contoller.dept.vo.DeptIdAndNameVO;
import cn.fmnx.oa.contoller.position.vo.PositionIdNameVO;
import cn.fmnx.oa.contoller.role.vo.RoleIdAndNameVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @ClassName UserVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
@Data
@ApiModel(description = "页面展示用户的数据对象")
public class UserVO implements Serializable {
    private static final long serialVersionUID = 4132835892267440302L;
    /**
     * 用户id
     */
    @ApiModelProperty(name = "userId",value = "用户id")
    private Integer userId;
    /**
     * 登录用户名
     */
    @ApiModelProperty(name = "userName",value = "登录用户名")
    private String userName;
    /**
     * //真实姓名
     */
    @ApiModelProperty(name = "realName",value = "真实姓名")
    private String realName;
    /**
     * 用户电话
     */
    @ApiModelProperty(name = "userTel",value = "手机号码")
    private String userTel;
    /**
     *  //邮件
     */
    @ApiModelProperty(name = "eamil",value = "邮箱")
    private String eamil;
    /**
     * //地址
     */
    @ApiModelProperty(name = "address",value = "地址")
    private String address;
    /**
     * //用户薪水
     */
    @ApiModelProperty(name = "salary",value = "用户薪水")
    private String salary;
    /**
     * 用户头像路径
     */
    @ApiModelProperty(name = "imgPath",value = "用户头像路径")
    private String imgPath;
    /**
     * //外键关联 职位表
     */
//    @ApiModelProperty(name = "positionId",value = "职位id")
//    private Integer positionId;
      private PositionIdNameVO positionIdNameVO;

    /**
     * //外键关联 部门表
     */
//    @ApiModelProperty(name = "deptId",value = "部门id")
//    private Integer deptId;
      private DeptIdAndNameVO deptIdAndNameVO;
    /**
     * //外键关联 角色表
     */
//    @ApiModelProperty(name = "roleId",value = "角色id")
//    private Integer roleId;
      private RoleIdAndNameVO roleIdAndNameVO;
}
