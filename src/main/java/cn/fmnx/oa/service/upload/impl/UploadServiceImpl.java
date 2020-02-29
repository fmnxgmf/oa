package cn.fmnx.oa.service.upload.impl;

import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.fastdfs.UploadProperties;
import cn.fmnx.oa.entity.attachment.Attachment;
import cn.fmnx.oa.service.upload.UploadService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @ClassName UploadServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
@Service
@Transactional
@EnableConfigurationProperties(UploadProperties.class)
@Slf4j
public class UploadServiceImpl implements UploadService {
    @Autowired
    /**
     * //文件上传最终地址
     */
    private FastFileStorageClient storageClient;
    @Resource
    private UploadProperties prop;

    @Override
    public String uploadImage(MultipartFile file) {
        String imgUrl = "";
        try {
            //检验格式
            String contentType = file.getContentType();
            System.out.println(prop.getAllowTypes());
            if (!prop.getAllowTypes().contains(contentType)) {
                throw new OaException(ExceptionEnum.CONTENT_TYPE_ERROE);
            }
            //检查内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                throw new OaException(ExceptionEnum.INVALID_IMAGE_ERROE);
            }
            //上传到fastdfs
            //获取图片格式 file.getOriginalFilename()得到上传时的文件名字
            String s = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), s, null);
            //返回该图片路径
            imgUrl = prop.getBaseUrl() + storePath.getFullPath();
            if (imgUrl != null && imgUrl != "") {
                return imgUrl;
            } else {
                log.error("文件上传失败");
                throw new OaException(ExceptionEnum.UPLOAD_FAILED_ERROE);
            }
        } catch (IOException e) {
            log.error("文件上传失败");
            throw new OaException(ExceptionEnum.UPLOAD_FAILED_ERROE);
        }

    }

    @Override
    public String uploadFile(MultipartFile file) {
        String filePath = "";
        try {
            if(file.getSize()==0){
                throw new OaException(ExceptionEnum.INVALID_IMAGE_ERROE);
            }
            String ext = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");

            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

            filePath = prop.getBaseUrl() + storePath.getFullPath();
            if (filePath != null && filePath != "") {
                return filePath;
            } else {
                log.error("文件上传失败");
                throw new OaException(ExceptionEnum.UPLOAD_FAILED_ERROE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @MethodName: uploadAttachment
     * @Description: 上传邮箱附件的逻辑处理，得到附件的存储路径
     * @Param: [file]
     * @Return: java.lang.String
     * @Author: gmf
     * @Date: 2020/2/23
    **/
    @Override
    public Attachment uploadAttachment(MultipartFile file) {
        String attachmentPath = "";
        Attachment attachment = new Attachment();
        try {
            if(file.getSize()==0){
                throw new OaException(ExceptionEnum.INVALID_IMAGE_ERROE);
            }
            //1.获取文件的后缀名,获取文件名
            String attachmentName = file.getOriginalFilename();
            String attachmentShuffix = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            //2.获取文件的类型
            String contentType = file.getContentType();
            //3.获取文件大小
            long size = file.getSize();
            //4.获取存取路径
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), size, attachmentShuffix, null);
            attachmentPath = prop.getBaseUrl()+storePath.getFullPath();
            if (attachmentPath != null && attachmentPath != "") {
                attachment.setAttachmentName(attachmentName);
                attachment.setAttachmentPath(attachmentPath);
                attachment.setAttachmentShuffix(attachmentShuffix);
                attachment.setAttachmentType(contentType);
                attachment.setAttachmentSize(size);
                return attachment;
            } else {
                log.error("文件上传失败");
                throw new OaException(ExceptionEnum.UPLOAD_FAILED_ERROE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File downFile(String groupName, String fileName) {
        String group = groupName.substring(groupName.indexOf("group1"),groupName.indexOf("/M00"));
        String path = groupName.substring(groupName.indexOf("M00/"));
        OutputStream out = null;
        try {
            String jar_parent = new File(ResourceUtils.getURL("classpath:").getPath())
                    .getParentFile()
                    .getParentFile()
                    .getParent();
           out = new FileOutputStream(jar_parent+File.separator+fileName);
            byte[] bytes = storageClient.downloadFile(group, path, new DownloadByteArray());
            out.write(bytes);
            return new File(jar_parent+File.separator+fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
