package cn.fmnx.oa.service.mailService;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.dto.AddMailAcountDTO;
import cn.fmnx.oa.contoller.mail.vo.*;

import java.util.List;

public interface MailNumService {
    boolean addMailAccount(AddMailAcountDTO addMailAcountDTO);

    PageResult<MailAccountsVO> findAllMailByUserId(Long userId, PageDTO pageDTO);

    PageResult<MailAccountsVO> findAllMailByLike(Long userId, PageDTO pageDTO, String condition);

    boolean updateMailAccount(AddMailAcountDTO mailAcountDTO);

    boolean deleteMailAccount(Long mailNumberId);

    List<MailNumVO> findMailNum(Long userId);

    List<MailNumTypeVO> findMailNumType();

    List<MailNumStatusVO> findMailNumStatus();

    List<MailBooksVO> findMailUsers(Long userId);
}
