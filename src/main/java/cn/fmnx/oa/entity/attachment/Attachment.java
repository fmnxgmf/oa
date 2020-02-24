package cn.fmnx.oa.entity.attachment;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Attachment
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/23
 * @Version V1.0
 **/
@Data
@Table(name="aoa_attachment_list")
public class Attachment {
    @Id
    @Column(name="attachment_id")
    @KeySql(useGeneratedKeys = true)
    /**
     * //附件id
     */
    private Long attachmentId;
    /**
     * //用户id 在没有连接外键只是用来 查询用户表的
     */
    @Column(name="user_id")
    private Long userId;

    /**
     * //附件名字
     */
    @Column(name="attachment_name")
    private String attachmentName;
    /**
     * //附件存储路径
     */
    @Column(name="attachment_path")
    private String attachmentPath;

    /**
     * //附件大小
     */
    @Column(name="attachment_size")
    private Long attachmentSize;
    /**
     * //附件类型
     */
    @Column(name="attachment_type")
    private String attachmentType;
    /**
     * //附件上传时间
     */
    @Column(name="upload_time")
    private Date uploadTime;
    /**
     * //所属模块
     */
    private String model;
    /**
     * //附件后缀
     */
    @Column(name="attachment_shuffix")
    private String attachmentShuffix;
}
