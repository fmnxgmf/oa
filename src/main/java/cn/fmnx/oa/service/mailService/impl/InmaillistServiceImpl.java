package cn.fmnx.oa.service.mailService.impl;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.dto.PushMailDTO;
import cn.fmnx.oa.contoller.mail.vo.DraftsBoxVO;
import cn.fmnx.oa.contoller.mail.vo.MailOutBoxVO;
import cn.fmnx.oa.contoller.mail.vo.MailStatusVO;
import cn.fmnx.oa.contoller.mail.vo.MailTypeVO;
import cn.fmnx.oa.entity.mail.Inmaillist;
import cn.fmnx.oa.entity.mail.Mailreciver;
import cn.fmnx.oa.mapper.mailMapper.InmaillistMapper;
import cn.fmnx.oa.mapper.mailMapper.MailreciverMapper;
import cn.fmnx.oa.service.mailService.InmaillistService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        String inReceiverName = pushMailDTO.getInReceiverName();
        if (inReceiverName==null || inReceiverName.equals("")){
            String[] split = pushMailDTO.getInReceiver().split(";");
            List<String> list = inmaillistMapper.findUserNameById(split);
            String str ="";
            for (String s : list) {
                if (str.equals("")){
                    str = str+s;
                }else {
                    str = str+";"+s;
                }
            }
            pushMailDTO.setInReceiverName(str);
        }

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

    @Override
    public PageResult<MailOutBoxVO> showMailOutBox(Long userId, PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<MailOutBoxVO> mailOutBoxVOS = inmaillistMapper.showMailOutBox(userId);
        for (MailOutBoxVO mailOutBoxVO : mailOutBoxVOS) {
            String[] split = mailOutBoxVO.getInReceiverId().split(";");
            String str="";
            if (split.length>0){
                List<String> list = inmaillistMapper.findUserNameById(split);
                for (String s : list) {

                    if (str.equals("")){
                        str = str+s;
                    }else {
                        str = str+";"+s;
                    }
                    mailOutBoxVO.setInReceiver(str);
                }
            }
        }
        PageResult<MailOutBoxVO> pageResult = new PageResult<>(new PageInfo<MailOutBoxVO>(mailOutBoxVOS));
        return pageResult;
    }

    @Override
    public boolean deleteMailOutBox(List<Long> mailIds) {
        Integer integer = inmaillistMapper.deleteMailOutBox(mailIds);
        if (integer==mailIds.size()){
            return true;
        }else {
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean setMailOutBoxStar(List<Map<Integer, Integer>> mailIds) {
        Integer integer = inmaillistMapper.setMailOutBoxStar(mailIds);
        if (integer >0){
            return true;
        }else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public PageResult<MailOutBoxVO> findMailOutBoxByLike(String condition, PageDTO pageDTO, Long userId) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<MailOutBoxVO> list = inmaillistMapper.findMailOutBoxByLike(condition,userId);
        PageResult<MailOutBoxVO> pageResult = new PageResult<>(new PageInfo<MailOutBoxVO>(list));
        return pageResult;
    }

    @Override
    public PageResult<DraftsBoxVO> findAllDraftsBoxVO(Long userId, PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<DraftsBoxVO> list = inmaillistMapper.findAllDraftsBox(userId);
        PageResult<DraftsBoxVO> pageResult = new PageResult<>(new PageInfo<DraftsBoxVO>(list));
        return pageResult;
    }

    @Override
    public boolean deleteDraftsBox(List<Long> mailIds) {
        Integer flag = inmaillistMapper.deleteDraftsBox(mailIds);
        if (flag > 0){
            return true;
        }
        return false;
    }

    @Override
    public DraftsBoxVO findOneDraftsBox(Long mailId, Long userId) {
        return inmaillistMapper.findOneDraftsBox(mailId,userId);

    }

    @Override
    public PageResult<DraftsBoxVO> findOneDraftsBoxByLike(PageDTO pageDTO, String condition, Long userId) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<DraftsBoxVO> list = inmaillistMapper.findOneDraftsBoxByLike(condition,userId);
        PageResult<DraftsBoxVO> pageResult = new PageResult<>(new PageInfo<DraftsBoxVO>(list));
        return pageResult;
    }
}
