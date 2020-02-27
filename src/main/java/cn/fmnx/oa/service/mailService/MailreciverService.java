package cn.fmnx.oa.service.mailService;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.vo.MailInBoxVO;
import cn.fmnx.oa.contoller.mail.vo.OneMailInBoxInfoVO;
import cn.fmnx.oa.contoller.mail.vo.ReplyMailInboxVO;

public interface MailreciverService {
    PageResult<MailInBoxVO> showMailInBox(Long userId, PageDTO pageDTO);

    PageResult<MailInBoxVO> findMailInBoxByLike(String condition, PageDTO pageDTO, Long userId);

    OneMailInBoxInfoVO findOneMailInBox(Long mailId, Long userId);

    ReplyMailInboxVO replyMail(Long userId, Long mailId);
}
