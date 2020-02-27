package cn.fmnx.oa.mapper.mailMapper;


import cn.fmnx.oa.contoller.mail.vo.MailAccountsVO;
import cn.fmnx.oa.contoller.mail.vo.MailBooksVO;
import cn.fmnx.oa.contoller.mail.vo.MailNumStatusVO;
import cn.fmnx.oa.contoller.mail.vo.MailNumTypeVO;
import cn.fmnx.oa.entity.mail.Mailnumber;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

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

    List<MailNumTypeVO> findMailNumType();


    List<MailNumStatusVO> findMailNumStatus();

    List<MailBooksVO> findMailUsers(@Param("userId") Long userId);

    Integer deleteMailInBox(@Param("mailIds") List<Long> mailIds);

    Integer setMailInBoxStar(@Param("mailIds") List<Map<Integer, Integer>> mailIds);

    Integer setMailInBoxRead(@Param("mailIds")List<Map<Integer, Integer>> mailIds);
}
