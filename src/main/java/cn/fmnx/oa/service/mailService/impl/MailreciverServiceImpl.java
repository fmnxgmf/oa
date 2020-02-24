package cn.fmnx.oa.service.mailService.impl;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.vo.MailInBoxVO;
import cn.fmnx.oa.mapper.mailMapper.MailreciverMapper;
import cn.fmnx.oa.service.mailService.MailreciverService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MailreciverServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/24
 * @Version V1.0
 **/
@Service
@Transactional
public class MailreciverServiceImpl implements MailreciverService {
    @Resource
    private MailreciverMapper mailreciverMapper;

    @Override
    public PageResult<MailInBoxVO> showMailInBox(Long userId, PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<MailInBoxVO> mailInBoxVOS = mailreciverMapper.showMailInBox(userId);
        PageResult<MailInBoxVO> pageResult = new PageResult<>(new PageInfo<MailInBoxVO>(mailInBoxVOS));
        return pageResult;
    }
}
