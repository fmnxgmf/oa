package cn.fmnx.oa.service.UserService;

import cn.fmnx.oa.entity.user.User;

public interface UserService {
    /**
     * 登录接口查找用户是否存在
     * @param userName
     * @param password
     * @return Integer
     */
    User findUserByNameAndPassword(String userName, String password);
    /**
     * @MethodName: findUserByUid
     * @Description: 根据user_ID查找user
     * @Param: [user]
     * @Return: cn.fmnx.oa.entity.user.User
     * @Author: gmf
     * @Date: 2020/2/10
    **/
    User findUserByUid(Integer uid);
}
