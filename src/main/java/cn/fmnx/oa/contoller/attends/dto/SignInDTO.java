package cn.fmnx.oa.contoller.attends.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SignInDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/3/1
 * @Version V1.0
 **/
@Data
@ApiModel(description = "签到的数据对象")
public class SignInDTO implements Serializable {
    private static final long serialVersionUID = -1539255890992793062L;
    @ApiModelProperty(name = "attendsUserId",value = "用户id")
    private Long attendsUserId;

}
