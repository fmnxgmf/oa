package cn.fmnx.oa.service.upload;

import cn.fmnx.oa.entity.attachment.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadImage(MultipartFile file);

    String  uploadFile(MultipartFile file);

    Attachment uploadAttachment(MultipartFile file);
}
