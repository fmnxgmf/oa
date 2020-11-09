package cn.fmnx.oa.service.mailService.impl;

import cn.fmnx.oa.contoller.mail.JavajmsUtils;
import cn.fmnx.oa.common.mail.JmsDTO;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.common.utils.CopyListutils;
import cn.fmnx.oa.contoller.mail.dto.AddMailAcountDTO;
import cn.fmnx.oa.contoller.mail.dto.PushExternalMailDTO;
import cn.fmnx.oa.contoller.mail.vo.*;
import cn.fmnx.oa.entity.attachment.Attachment;
import cn.fmnx.oa.entity.mail.Inmaillist;
import cn.fmnx.oa.entity.mail.Mailnumber;
import cn.fmnx.oa.mapper.attachmentMapper.AttachmentMapper;
import cn.fmnx.oa.mapper.mailMapper.InmaillistMapper;
import cn.fmnx.oa.mapper.mailMapper.MailNumberMapper;
import cn.fmnx.oa.service.mailService.MailNumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MailNumServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/19
 * @Version V1.0
 **/
@Service
@Transactional
public class MailNumServiceImpl implements MailNumService {
    @Resource
    private MailNumberMapper mailNumberMapper;
    @Resource
    private InmaillistMapper inmaillistMapper;
    @Resource
    private AttachmentMapper attachmentMapper;
    @Autowired
    private JavajmsUtils jmsutils;
    @Override
    public boolean addMailAccount(AddMailAcountDTO addMailAcountDTO) {
        Mailnumber mailnumber = new Mailnumber();
        addMailAcountDTO.setMailCreateTime(new Date());
        BeanUtils.copyProperties(addMailAcountDTO,mailnumber);

        if (!StringUtils.isEmpty(mailnumber)){
            int insert = mailNumberMapper.insert(mailnumber);
            if (insert > 0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public PageResult<MailAccountsVO> findAllMailByUserId(Long userId, PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<MailAccountsVO> list = mailNumberMapper.findMailByUserId(userId);
        PageResult<MailAccountsVO> pageResult = new PageResult<>(new PageInfo<MailAccountsVO>(list));
        if (!StringUtils.isEmpty(pageResult)){
            return pageResult;
        }
        return null;
    }

    @Override
    public PageResult<MailAccountsVO> findAllMailByLike(Long userId, PageDTO pageDTO, String condition) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<MailAccountsVO> list = mailNumberMapper.findMailByLike(userId,condition);
        PageResult<MailAccountsVO> pageResult = new PageResult<>(new PageInfo<MailAccountsVO>(list));
        if (!StringUtils.isEmpty(pageResult)){
            return pageResult;
        }
        return null;
    }

    @Override
    public boolean updateMailAccount(AddMailAcountDTO mailAcountDTO) {
        Mailnumber mailnumber = new Mailnumber();
        BeanUtils.copyProperties(mailAcountDTO,mailnumber);;
        if (!StringUtils.isEmpty(mailnumber)){
            int i = mailNumberMapper.updateByPrimaryKey(mailnumber);
            if (i > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMailAccount(Long mailNumberId) {
        int i = mailNumberMapper.deleteByPrimaryKey(mailNumberId);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<MailNumVO> findMailNum(Long userId) {
        Mailnumber mailnumber = new Mailnumber();
        mailnumber.setMailUserId(userId);
        List<Mailnumber> mailnumbers = mailNumberMapper.select(mailnumber);
        List<MailNumVO> mailNumVOS = new ArrayList<>();
        if (mailnumbers!=null && mailnumbers.size()>0){
            CopyListutils.copyListBeanUtils(mailnumbers,mailNumVOS,MailNumVO.class);
            if (!CollectionUtils.isEmpty(mailNumVOS)){
                return mailNumVOS;
            }
        }
        return null;
    }

    @Override
    public List<MailNumTypeVO> findMailNumType() {
        List<MailNumTypeVO> mailNumTypeVOS = mailNumberMapper.findMailNumType();
        if (!CollectionUtils.isEmpty(mailNumTypeVOS)){
            return mailNumTypeVOS;
        }
        return null;
    }

    @Override
    public List<MailNumStatusVO> findMailNumStatus() {
        List<MailNumStatusVO> mailNumStatusVOS = mailNumberMapper.findMailNumStatus();
        if (!CollectionUtils.isEmpty(mailNumStatusVOS)){
            return mailNumStatusVOS;
        }
        return null;
    }

    @Override
    public List<MailBooksVO> findMailUsers(Long userId) {
        List<MailBooksVO> mailBooksVOS = mailNumberMapper.findMailUsers(userId);
        if (!CollectionUtils.isEmpty(mailBooksVOS)){
            return mailBooksVOS;
        }
        return null;
    }

    @Override
    public boolean deletemailInBox(List<Long> mailIds) {
       Integer flag = mailNumberMapper.deleteMailInBox(mailIds);
       if (flag == mailIds.size()){
           return true;
       }else {
           //手动事务强制回滚
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           return false;
       }
    }

    @Override
    public boolean setMailInBoxStar(List<Map<Integer, Integer>> mailIds) {
        Integer integer = mailNumberMapper.setMailInBoxStar(mailIds);
        if (integer > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean setMailInBoxRead(List<Map<Integer, Integer>> mailIds) {
        Integer i = mailNumberMapper.setMailInBoxRead(mailIds);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<ExternalMailVO> findExternalMail(Long userId) {
        Example example = new Example(Mailnumber.class);
        Example.Criteria criteria = example.createCriteria();
        if (userId != null){
            criteria.andEqualTo("mailUserId",userId);
        }
        List<Mailnumber> mailnumbers = mailNumberMapper.selectByExample(example);
        List<ExternalMailVO> list = new ArrayList<>();
        CopyListutils.copyListBeanUtils(mailnumbers,list,ExternalMailVO.class);
        if (!CollectionUtils.isEmpty(list)){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public boolean pushExternalMail(PushExternalMailDTO pushExternalMailDTO) {
        //1.判断收件人属于那种邮箱所对应的smtp服务器
        String[] split = pushExternalMailDTO.getInReceiverName().split(";");
        for (String s : split) {
            Example example = new Example(Mailnumber.class);
            Example.Criteria criteria = example.createCriteria();
            Example.Criteria mailNumberId = criteria.andEqualTo("mailNumberId", pushExternalMailDTO.getMailNumberid());
            Mailnumber mailnumber = mailNumberMapper.selectOneByExample(example);
            String ext = org.apache.commons.lang3.StringUtils.substringAfterLast(mailnumber.getMailAccount(), "@");
            JmsDTO jmsDTO = new JmsDTO();
            //设置授权码
            jmsDTO.setPassword(mailnumber.getPassword());
            //设置发送者账户
            jmsDTO.setUsername(mailnumber.getMailAccount());
            //设置接收者
            jmsDTO.setTo(s);
            //设置主题
            jmsDTO.setSubject(pushExternalMailDTO.getMailTitle());
            //设置邮件内容
            jmsDTO.setText(pushExternalMailDTO.getContent());
            //设置附件
            if(pushExternalMailDTO.getMailFileid()!=null){
                Example attach = new Example(Attachment.class);
                attach.createCriteria().andEqualTo("attachmentId",pushExternalMailDTO.getMailFileid());
                Attachment attachment = attachmentMapper.selectOneByExample(attach);
                jmsDTO.setFilePath(attachment.getAttachmentPath());
                jmsDTO.setAttachmentName(attachment.getAttachmentName());
            }
            if (ext.equalsIgnoreCase("qq.com")){
                jmsDTO.setHost("smtp.qq.com");
                boolean flag = jmsutils.pushExternalMail(jmsDTO);
                if (!flag){
                    return false;
                }
            } else if (ext.equalsIgnoreCase("163.com")){
                jmsDTO.setHost("smtp.163.com");
                boolean flag = jmsutils.pushExternalMail(jmsDTO);
                if (!flag){
                    return false;
                }
            }
        }
        //都执行成功后再将信息存入aoa_in_mail_list即可，因为外部邮件收件箱不用存在
        pushExternalMailDTO.setMailCreateTime(new Date());
        //未删除
        pushExternalMailDTO.setDel(0);
        //为表星
        pushExternalMailDTO.setStar(0);
        //发送成功
        pushExternalMailDTO.setPush(0);
        Inmaillist inmaillist = new Inmaillist();
        BeanUtils.copyProperties(pushExternalMailDTO,inmaillist);
        if (!StringUtils.isEmpty(inmaillist)){
            inmaillistMapper.insert(inmaillist);
        }

        return true;
    }
}
