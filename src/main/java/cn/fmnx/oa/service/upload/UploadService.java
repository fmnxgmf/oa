package cn.fmnx.oa.service.upload;

import cn.fmnx.oa.entity.attachment.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UploadService {
    String uploadImage(MultipartFile file);

    String  uploadFile(MultipartFile file);

    Attachment uploadAttachment(MultipartFile file);
    File downFile(String groupName,String fileName);
}
