package cn.fmnx.oa.mapper.attendsMapper;

import cn.fmnx.oa.entity.attends.Attends;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AttendsMapper extends Mapper<Attends> {
    Integer findSignInCount(@Param("attendsUserId") Long attendsUserId, @Param("ymdDate") String ymdDate);
}
