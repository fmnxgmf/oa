package cn.fmnx.oa.mapper.userMapper;

import cn.fmnx.oa.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    /**
     * @MethodName: findUserByNameAndPassword
     * @Description:
     * @Param: [userName, password]
     * @Return: java.lang.Integer
     * @Author: gmf
     * @Date: 2020/2/8
    **/
    @Select("select user.user_name,user.user_id from aoa_user user where user.user_name = #{userName} and user.password = #{password}")
     User findUserByNameAndPassword(@Param("userName")String userName,@Param("password")String password);


}
