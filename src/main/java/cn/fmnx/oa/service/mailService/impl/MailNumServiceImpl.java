package cn.fmnx.oa.service.mailService.impl;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.dto.AddMailAcountDTO;
import cn.fmnx.oa.contoller.mail.vo.MailAccountsVO;
import cn.fmnx.oa.entity.mail.Mailnumber;
import cn.fmnx.oa.mapper.mailMapper.MailNumberMapper;
import cn.fmnx.oa.service.mailService.MailNumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
}
