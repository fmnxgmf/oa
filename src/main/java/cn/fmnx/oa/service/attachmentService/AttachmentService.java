package cn.fmnx.oa.service.attachmentService;

import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Long uploadAttachment(MultipartFile file, Long userId);
}
