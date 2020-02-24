package cn.fmnx.oa.service.attachmentService.impl;

import cn.fmnx.oa.entity.attachment.Attachment;
import cn.fmnx.oa.mapper.attachmentMapper.AttachmentMapper;
import cn.fmnx.oa.service.attachmentService.AttachmentService;
import cn.fmnx.oa.service.upload.impl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName AttachmentServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/23
 * @Version V1.0
 **/
@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {
    @Resource
    private AttachmentMapper attachmentMapper;
    @Autowired
    private UploadServiceImpl uploadService;

    @Override
    public Long uploadAttachment(MultipartFile file, Long userId) {
        Attachment attachment = uploadService.uploadAttachment(file);
        attachment.setUploadTime(new Date());
        attachment.setUserId(userId);
        attachment.setModel("mail");
        int insert = attachmentMapper.insert(attachment);
        if (insert > 0){
            return attachment.getAttachmentId();
        }else {
            return null;
        }

    }
}
