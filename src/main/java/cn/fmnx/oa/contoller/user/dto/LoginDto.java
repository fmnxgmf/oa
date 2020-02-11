package cn.fmnx.oa.contoller.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName LoginDto
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/8
 * @Version V1.0
 **/
@Data
@ApiModel(description = "接收用户登录参数的Model对象")
public class LoginDto implements Serializable {
    private static final long serialVersionUID = 2148673419906598418L;
    /**
     * 登录用户名
     */
    @ApiModelProperty(value = "登录用户名",name = "userName",required = true,example = "test")
  private String userName;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码",name = "password",required = true,example = "123456")
  private String password;
    /**
     * 图片验证码
     */
    @ApiModelProperty(value = "验证码",name = "code",required = true,example = "1a34")
  private String code;
    /**
     * 设备唯一id
     */
    @ApiModelProperty(value = "设备唯一性id",name = "deviceId",required = true,example = "1B380160DB6D8E42B2B081F60849FC36")
  private String deviceId;
}
