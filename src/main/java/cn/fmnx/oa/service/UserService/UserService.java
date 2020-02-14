package cn.fmnx.oa.service.UserService;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.user.dto.UserDTO;
import cn.fmnx.oa.contoller.user.vo.UserVO;
import cn.fmnx.oa.entity.user.User;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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

    boolean addUser(UserDTO userDTO);

    boolean updateUser(UserDTO userDTO);

    PageResult<UserVO> findAllUser(PageDTO pageDTO);

    UserVO findOneUserById(Long userId);

    boolean deleteUserById(Long userId);

    PageResult<UserVO> findLikeUser(String condition, @Validated PageDTO pageDTO);
}
