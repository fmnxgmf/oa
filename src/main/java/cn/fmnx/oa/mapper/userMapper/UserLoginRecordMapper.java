package cn.fmnx.oa.mapper.userMapper;

import cn.fmnx.oa.contoller.user.vo.UserLoginRecordVO;
import cn.fmnx.oa.entity.user.UserLoginRecord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserLoginRecordMapper extends Mapper<UserLoginRecord> {
    List<UserLoginRecordVO> showUserLoginRecord();

    List<UserLoginRecordVO> showLikeUserLoginRecord(@Param("condition") String condition);
}
