package cn.fmnx.oa.service.mailService;

import cn.fmnx.oa.contoller.mail.dto.PushMailDTO;
import cn.fmnx.oa.contoller.mail.vo.MailStatusVO;
import cn.fmnx.oa.contoller.mail.vo.MailTypeVO;

import java.util.List;

public interface InmaillistService {
    List<MailTypeVO> findInMailListType();

    List<MailStatusVO> findMailStatus();

    boolean pushMail(PushMailDTO pushMailDTO);
}
