package cn.fmnx.oa.contoller.mail;

import cn.fmnx.oa.common.mail.JmsDTO;
import cn.fmnx.oa.service.upload.impl.UploadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * @ClassName Javajms
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/29
 * @Version V1.0
 **/
@Slf4j
@Component
public class JavajmsUtils {
    @Autowired
    private UploadServiceImpl uploadService;
    public boolean pushExternalMail(JmsDTO jmsDTO) {
        try {
        JavaMailSenderImpl jms = new JavaMailSenderImpl();
        jms.setHost(jmsDTO.getHost());
        jms.setUsername(jmsDTO.getUsername());
        jms.setPassword(jmsDTO.getPassword());
        jms.setPort(465);
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtp.socketFactory.port", "465");
        p.setProperty("mail.smtp.port", "465");
        jms.setJavaMailProperties(p);
        //建立邮箱消息
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(message,true,"UTF-8");
        helper.setFrom(jmsDTO.getUsername());
        helper.setTo(jmsDTO.getTo());
        helper.setSubject(jmsDTO.getSubject());
        helper.setText(jmsDTO.getText(),true);
        String filePath = jmsDTO.getFilePath();
        if (!StringUtils.isEmpty(jmsDTO.getFilePath())&& !StringUtils.isEmpty(jmsDTO.getAttachmentName())){
            File file1 = uploadService.downFile(jmsDTO.getFilePath(), jmsDTO.getAttachmentName());
            FileSystemResource file = new FileSystemResource(file1);
            helper.addAttachment(jmsDTO.getAttachmentName(), file);
            jms.send(message);
            log.info("邮件投递成功!");
            file1.delete();

        }else {
            jms.send(message);
            log.info("邮件投递成功!");
        }
        return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("邮件投递失败!");
            return false;
        }
    }
}
