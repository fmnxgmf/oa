package cn.fmnx.oa.mapper.mailMapper;


import cn.fmnx.oa.contoller.mail.vo.MailAccountsVO;
import cn.fmnx.oa.entity.mail.Mailnumber;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName MailMapper
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/19
 * @Version V1.0
 **/
public interface MailNumberMapper extends Mapper<Mailnumber> {
    List<MailAccountsVO> findMailByUserId(@Param("userId") Long userId);

    List<MailAccountsVO> findMailByLike(@Param("userId") Long userId, @Param("condition") String condition);
}
