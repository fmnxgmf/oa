package cn.fmnx.oa.service.upload.impl;

import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.fastdfs.UploadProperties;
import cn.fmnx.oa.service.upload.UploadService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        if (! prop.getAllowTypes().contains(contentType)){
            throw new OaException(ExceptionEnum.CONTENT_TYPE_ERROE);
        }
        //检查内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null){
                throw new OaException(ExceptionEnum.INVALID_IMAGE_ERROE);
            }
        //上传到fastdfs
        //获取图片格式 file.getOriginalFilename()得到上传时的文件名字
            String s = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), s, null);
            //返回该图片路径
             imgUrl = prop.getBaseUrl()+storePath.getFullPath();
             if (imgUrl != null && imgUrl != ""){
                 return imgUrl;
             }else {
                 log.error("文件上传失败");
                 throw new OaException(ExceptionEnum.UPLOAD_FAILED_ERROE);
             }
        } catch (IOException e) {
            log.error("文件上传失败");
            throw new OaException(ExceptionEnum.UPLOAD_FAILED_ERROE);
        }

    }
}