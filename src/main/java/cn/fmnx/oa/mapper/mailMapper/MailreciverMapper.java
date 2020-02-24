package cn.fmnx.oa.mapper.mailMapper;

import cn.fmnx.oa.contoller.mail.vo.MailInBoxVO;
import cn.fmnx.oa.entity.mail.Mailreciver;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MailreciverMapper extends Mapper<Mailreciver> {
    List<MailInBoxVO> showMailInBox(@Param("userId") Long userId);
}
