package cn.fmnx.oa.service.UserService.impl;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.user.vo.UserLoginRecordVO;
import cn.fmnx.oa.entity.user.UserLoginRecord;
import cn.fmnx.oa.mapper.userMapper.UserLoginRecordMapper;
import cn.fmnx.oa.service.UserService.UserLoginRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserLoginRecordServiceImpl
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/16
 * @Version V1.0
 **/
@Service
@Transactional
public class UserLoginRecordServiceImpl implements UserLoginRecordService {
    @Resource
    private UserLoginRecordMapper userLoginRecordMapper;

    @Override
    public boolean addUserLoginRecord(UserLoginRecord userLog) {
        int insert = userLoginRecordMapper.insert(userLog);
        if (insert > 0){
            return true;
        }
        return false;
    }

    @Override
    public PageResult<UserLoginRecordVO> showUserLoginRecord(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<UserLoginRecordVO> list = userLoginRecordMapper.showUserLoginRecord();
        PageResult<UserLoginRecordVO> pageResult = new PageResult<>(new PageInfo<UserLoginRecordVO>(list));
        return pageResult;
    }

    @Override
    public PageResult<UserLoginRecordVO> showLikeUserLoginRecord(PageDTO pageDTO, String condition) {
        PageHelper.startPage(pageDTO.getPageNum(),pageDTO.getPageSize());
        List<UserLoginRecordVO> list = userLoginRecordMapper.showLikeUserLoginRecord(condition);
        PageResult<UserLoginRecordVO> pageResult = new PageResult<>(new PageInfo<UserLoginRecordVO>(list));
        return pageResult;
    }
}
