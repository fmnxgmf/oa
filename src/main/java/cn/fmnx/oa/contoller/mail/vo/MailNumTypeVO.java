package cn.fmnx.oa.contoller.mail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MailNumTypeVO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/24
 * @Version V1.0
 **/
@Data
@ApiModel(description = "邮箱的分类(1=系统邮件，2=公司邮件)")
public class MailNumTypeVO implements Serializable {
    private static final long serialVersionUID = 2489452744504718354L;
    @ApiModelProperty(name = "typeId",value = "类型id")
    private Long typeId;
    @ApiModelProperty(name = "typeName",value = "类型名字")
    private String typeName;
}
