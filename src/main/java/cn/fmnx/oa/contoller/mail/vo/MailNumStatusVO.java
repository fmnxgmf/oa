package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MailNumStatusVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/24
 * @Version V1.0
 **/
@Data
@ApiModel(description = "邮箱的状态(1=有效，2=无效)")
public class MailNumStatusVO implements Serializable {
    private static final long serialVersionUID = -4740815666614394279L;
    @ApiModelProperty(name = "statusId",value = "状态id")
    private Long statusId;
    @ApiModelProperty(name = "statusName",value = "状态值名字")
    private String statusName;
}
