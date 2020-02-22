package cn.fmnx.oa.service.UserService;

import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.user.vo.UserLoginRecordVO;
import cn.fmnx.oa.entity.user.UserLoginRecord;

public interface UserLoginRecordService {
    boolean addUserLoginRecord(UserLoginRecord userLog);

    PageResult<UserLoginRecordVO> showUserLoginRecord(PageDTO pageDTO);

    PageResult<UserLoginRecordVO> showLikeUserLoginRecord(PageDTO pageDTO, String condition);
}
