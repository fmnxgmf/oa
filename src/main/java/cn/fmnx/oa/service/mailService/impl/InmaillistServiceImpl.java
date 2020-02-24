package cn.fmnx.oa.service.mailService.impl;

import cn.fmnx.oa.contoller.mail.dto.PushMailDTO;
import cn.fmnx.oa.contoller.mail.vo.MailStatusVO;
import cn.fmnx.oa.contoller.mail.vo.MailTypeVO;
import cn.fmnx.oa.entity.mail.Inmaillist;
import cn.fmnx.oa.entity.mail.Mailreciver;
import cn.fmnx.oa.mapper.mailMapper.InmaillistMapper;
import cn.fmnx.oa.mapper.mailMapper.MailreciverMapper;
import cn.fmnx.oa.service.mailService.InmaillistService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName InmaillistServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/24
 * @Version V1.0
 **/
@Service
@Transactional
public class InmaillistServiceImpl implements InmaillistService {
    @Resource
    private InmaillistMapper inmaillistMapper;
    @Resource
    private MailreciverMapper mailreciverMapper;
    @Override
    public List<MailTypeVO> findInMailListType() {
        List<MailTypeVO> mailTypeVOS = inmaillistMapper.findInMailListType();
        if(!CollectionUtils.isEmpty(mailTypeVOS)){
            return mailTypeVOS;
        }else {
            return null;
        }

    }

    @Override
    public List<MailStatusVO> findMailStatus() {
        List<MailStatusVO> mailStatusVOS = inmaillistMapper.findMailStatus();
        if (!CollectionUtils.isEmpty(mailStatusVOS)){
            return mailStatusVOS;
        }
        return null;
    }

    @Override
    public boolean pushMail(PushMailDTO pushMailDTO) {
        pushMailDTO.setMailCreateTime(new Date());
        //未删除
        pushMailDTO.setDel(0);
        //为表星
        pushMailDTO.setStar(0);
        //发送成功
        pushMailDTO.setPush(0);
        Inmaillist inmaillist = new Inmaillist();
        BeanUtils.copyProperties(pushMailDTO,inmaillist);
        if (!StringUtils.isEmpty(inmaillist)){
            int insert = inmaillistMapper.insert(inmaillist);
            //邮箱发送成功还需要给接收表(aoa_mail_reciver)里面插入数据
            if (insert > 0){
                //对邮件接收者进行存在
                String[] split = inmaillist.getInReceiver().split(";");
                for (String s : split) {
                    Mailreciver mailreciver = new Mailreciver();
                    mailreciver.setDel(0);
                    mailreciver.setMailId(inmaillist.getMailId());
                    mailreciver.setRead(0);
                    mailreciver.setStar(0);
                    mailreciver.setReciverId(Long.parseLong(s));
                    int flag = mailreciverMapper.insert(mailreciver);
                    if (flag <= 0){
                        return false;
                    }
                }

                return true;
            }else {
                //发送失败
                inmaillist.setPush(1);
                inmaillistMapper.insert(inmaillist);

            }
        }
        return false;
    }
}
