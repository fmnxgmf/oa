package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ExternalMailVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/28
 * @Version V1.0
 **/
@Data
@ApiModel(description = "外部邮箱账户")
public class ExternalMailVO implements Serializable {
    @ApiModelProperty(name = "mailUserName",value = "外部邮箱账户的别名",example = "李四")
    private String mailUserName;
    @ApiModelProperty(name = "mailNumberId",value = "外部邮箱的主键id")
    private Long mailNumberId;
    @ApiModelProperty(name = "mailAccount",value = "外部邮箱账户")
    private String mailAccount;

}

