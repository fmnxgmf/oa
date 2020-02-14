package cn.fmnx.oa.service.UserService.impl;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.user.dto.UserDTO;
import cn.fmnx.oa.contoller.user.vo.UserVO;
import cn.fmnx.oa.entity.user.User;
import cn.fmnx.oa.mapper.userMapper.UserMapper;
import cn.fmnx.oa.service.UserService.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public boolean addUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        if (!StringUtils.isEmpty(user)){
            int insert = userMapper.insert(user);
            if (insert > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        if (!StringUtils.isEmpty(user)){
            int insert = userMapper.updateByPrimaryKey(user);
            if (insert > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public PageResult<UserVO> findAllUser(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<UserVO> allUserInfo = userMapper.findAllUserInfo();
        PageResult<UserVO> pageResult = new PageResult(new PageInfo<UserVO>(allUserInfo));
        if (!CollectionUtils.isEmpty(pageResult.getItems())){
            return pageResult;
        }
        return null;
    }

    @Override
    public UserVO findOneUserById(Long userId) {
        UserVO userVO = userMapper.findUserById(userId);
        if (!StringUtils.isEmpty(userVO)){
            return userVO;
        }
        return null;
    }

    @Override
    public boolean deleteUserById(Long userId) {
        int i = userMapper.deleteByPrimaryKey(userId);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public PageResult<UserVO> findLikeUser(String condition, PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<UserVO> userVOS =  userMapper.findLikeUser(condition);
        PageResult<UserVO> pageResult = new PageResult<>(new PageInfo<UserVO>(userVOS));
        return null;
    }
}
