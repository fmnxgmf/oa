package cn.fmnx.oa.contoller.attachment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName AddAttachment
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/23
 * @Version V1.0
 **/
@Data
@ApiModel(description = "附件上传所需要的数据对象")
public class AddAttachmentDTO {

    /**
     * //附件id
     */
    @ApiModelProperty(name = "attachmentId",value = "附件id")
    private Long attachmentId;
    /**
     * //用户id 在没有连接外键只是用来 查询用户表的
     */
    @ApiModelProperty(name = "userId",value = "用户id")
    private String userId;

    /**
     * //附件名字
     */
    @ApiModelProperty(name = "attachmentName",value = "附件名字")
    private String attachmentName;
    /**
     * //附件存储路径
     */
    @ApiModelProperty(name = "attachmentPath",value = "附件存储路径")
    private String attachmentPath;

    /**
     * //附件大小
     */
    @ApiModelProperty(name = "attachmentSize",value = "附件大小")
    private Long attachmentSize;
    /**
     * //附件类型
     */
    @ApiModelProperty(name = "attachmentType",value = "附件类型")
    private String attachmentType;
    /**
     * //附件上传时间
     */
    @ApiModelProperty(name = "uploadTime",value = "附件上传时间")
    private Date uploadTime;
    /**
     * //所属模块
     */
    @ApiModelProperty(name = "model",value = "所属模块")
    private String model;
    /**
     * //附件后缀
     */
    @ApiModelProperty(name = "attachmentShuffix",value = "附件后缀")
    private String attachmentShuffix;
}
