package cn.fmnx.oa.service.UserService.impl;

import cn.fmnx.oa.entity.user.User;
import cn.fmnx.oa.mapper.userMapper.UserMapper;
import cn.fmnx.oa.service.UserService.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/8
 * @Version V1.0
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * @MethodName:
     * @Description: 根据用户姓名查询用户
     * @Param:
     * @Return:
     * @Author: gmf
     * @Date: 2020/2/8
    **/
    @Override
    public User findUserByNameAndPassword(String userName, String password) {
        User user = userMapper.findUserByNameAndPassword(userName, password);
        return user;
    }

    @Override
    public User findUserByUid(Integer uid) {

       return userMapper.selectByPrimaryKey(uid);
    }
}
