package cn.fmnx.oa.mapper.mailMapper;

import cn.fmnx.oa.contoller.mail.vo.DraftsBoxVO;
import cn.fmnx.oa.contoller.mail.vo.MailOutBoxVO;
import cn.fmnx.oa.contoller.mail.vo.MailStatusVO;
import cn.fmnx.oa.contoller.mail.vo.MailTypeVO;
import cn.fmnx.oa.entity.mail.Inmaillist;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface InmaillistMapper extends Mapper<Inmaillist> {
    List<MailTypeVO> findInMailListType();

    List<MailStatusVO> findMailStatus();

    List<MailOutBoxVO> showMailOutBox(@Param("userId") Long userId);

    List<String> findUserNameById(String[] array);

    Integer deleteMailOutBox(@Param("mailIds") List<Long> mailIds);

    Integer setMailOutBoxStar(@Param("mailIds") List<Map<Integer, Integer>> mailIds);

    List<MailOutBoxVO> findMailOutBoxByLike(@Param("condition") String condition, @Param("userId") Long userId);

    List<DraftsBoxVO> findAllDraftsBox(@Param("userId") Long userId);

    Integer deleteDraftsBox(@Param("mailIds") List<Long> mailIds);
}
