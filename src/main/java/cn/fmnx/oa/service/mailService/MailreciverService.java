package cn.fmnx.oa.service.mailService;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.vo.MailInBoxVO;

public interface MailreciverService {
    PageResult<MailInBoxVO> showMailInBox(Long userId, PageDTO pageDTO);
}
