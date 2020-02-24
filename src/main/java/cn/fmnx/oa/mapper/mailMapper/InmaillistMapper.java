package cn.fmnx.oa.mapper.mailMapper;

import cn.fmnx.oa.contoller.mail.vo.MailStatusVO;
import cn.fmnx.oa.contoller.mail.vo.MailTypeVO;
import cn.fmnx.oa.entity.mail.Inmaillist;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface InmaillistMapper extends Mapper<Inmaillist> {
    List<MailTypeVO> findInMailListType();

    List<MailStatusVO> findMailStatus();
}
