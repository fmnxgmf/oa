package cn.fmnx.oa.service.mailService;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.dto.PushMailDTO;
import cn.fmnx.oa.contoller.mail.vo.DraftsBoxVO;
import cn.fmnx.oa.contoller.mail.vo.MailOutBoxVO;
import cn.fmnx.oa.contoller.mail.vo.MailStatusVO;
import cn.fmnx.oa.contoller.mail.vo.MailTypeVO;

import java.util.List;
import java.util.Map;

public interface InmaillistService {
    List<MailTypeVO> findInMailListType();

    List<MailStatusVO> findMailStatus();

    boolean pushMail(PushMailDTO pushMailDTO);

    PageResult<MailOutBoxVO> showMailOutBox(Long userId, PageDTO pageDTO);

    boolean deleteMailOutBox(List<Long> mailIds);

    boolean setMailOutBoxStar(List<Map<Integer, Integer>> mailIds);

    PageResult<MailOutBoxVO> findMailOutBoxByLike(String condition, PageDTO pageDTO, Long userId);

    PageResult<DraftsBoxVO> findAllDraftsBoxVO(Long userId, PageDTO pageDTO);

    boolean deleteDraftsBox(List<Long> mailIds);
}
