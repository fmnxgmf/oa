package cn.fmnx.oa.contoller.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserLoginRecordVO
 * @Description: 在线用户的数据展示
 * @Author gmf
 * @Date 2020/2/16
 * @Version V1.0
 **/
@Data
@ApiModel(description = "在线用户的数据展示")
public class UserLoginRecordVO implements Serializable {
    private static final long serialVersionUID = -361481806276103954L;
    @ApiModelProperty(name = "userName",value = "登录的用户名")
    private String userName;
    @ApiModelProperty(name = "loginTime",value = "登录的时间")
    private String loginTime;
    @ApiModelProperty(name = "ipAddr",value = "登录的id地址")
    private String ipAddr;
    @ApiModelProperty(name = "browser",value = "登录用的浏览器及版本型号")
    private String browser;
}
