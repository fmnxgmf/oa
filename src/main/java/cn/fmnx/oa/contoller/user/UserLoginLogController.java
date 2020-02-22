package cn.fmnx.oa.contoller.user;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.common.utils.GetUserInfoUtils;
import cn.fmnx.oa.contoller.user.vo.UserLoginRecordVO;
import cn.fmnx.oa.entity.user.UserLoginRecord;
import cn.fmnx.oa.service.UserService.impl.UserLoginRecordServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName UserLoginLogController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/15
 * @Version V1.0
 **/
@RestController
@Api(tags = "用户登录记录的接口")
public class UserLoginLogController {
    @Autowired
    private UserLoginRecordServiceImpl userLoginRecordService;

    /**
     * @MethodName: addUserLoginRecord
     * @Description: 用户登录记录数据的接口
     * @Param: [request]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/16
     **/
    @ApiOperation(value = "用户登录记录数据的接口")
    @PostMapping("/userlog")
    public ResultModel addUserLoginRecord(HttpServletRequest request) {
        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = sdf.format(date);

        //对数据进行封装
        UserLoginRecord userLog = new UserLoginRecord();
        userLog.setBrowser(GetUserInfoUtils.getRequestBrowserInfo(request));
        userLog.setIpAddr(GetUserInfoUtils.getIpAddr(request));
        userLog.setLoginTime(date);
        userLog.setSessionId(GetUserInfoUtils.getSessionId(request));
        userLog.setUserId(GetUserInfoUtils.getUserId(request));
        boolean flag = userLoginRecordService.addUserLoginRecord(userLog);
        if (flag) {
            return ResultModel.ok("用户登录记录保存成功");
        } else {
            throw new OaException(ExceptionEnum.USER_LOGIN_RECORD_FAIL);
        }
    }

    /**
     * @MethodName: showUserLoginRecord
     * @Description: 展示在线用户数据
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.common.page.PageResult < cn.fmnx.oa.contoller.user.vo.UserLoginRecordVO>>
     * @Author: gmf
     * @Date: 2020/2/16
     **/
    @ApiOperation(value = "展示在线用户数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码，默认值1", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小，默认值10", required = false, dataType = "int")
    })
    @GetMapping("/showUserLoginRecord")
    public ResultModel<PageResult<UserLoginRecordVO>> showUserLoginRecord(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        PageDTO pageDTO;
        if (pageNum != null && pageSize != null) {
            pageDTO = new PageDTO(pageNum, pageSize);
        } else {
            pageDTO = new PageDTO();
        }
        PageResult<UserLoginRecordVO> pageResult = userLoginRecordService.showUserLoginRecord(pageDTO);
        if (!CollectionUtils.isEmpty(pageResult.getItems())) {
            return ResultModel.ok(pageResult);
        } else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }

    /**
     * @MethodName: likeUserLoginRecord
     * @Description: 在线用户的模糊查询
     * @Param: [condition, pageNum, pageSize]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.common.page.PageResult < cn.fmnx.oa.contoller.user.vo.UserLoginRecordVO>>
     * @Author: gmf
     * @Date: 2020/2/16
     **/
    @ApiOperation(value = "在线用户的模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询的条件", required = false),
            @ApiImplicitParam(name = "pageNum", value = "分页页码", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = false, dataType = "int")
    })
    @GetMapping("/likeUserLoginRecord")
    public ResultModel<PageResult<UserLoginRecordVO>> likeUserLoginRecord(@RequestParam(value = "condition",required = false) String condition,
                                                                          @RequestParam(value = "pageNum", required = false) Integer pageNum,
                                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        PageDTO pageDTO;
        if (pageNum != null && pageSize != null) {
            pageDTO = new PageDTO(pageNum, pageSize);
        } else {
            pageDTO = new PageDTO();
        }
        PageResult<UserLoginRecordVO> pageResult = userLoginRecordService.showLikeUserLoginRecord(pageDTO, condition);
        if (!CollectionUtils.isEmpty(pageResult.getItems())) {
            return ResultModel.ok(pageResult);
        } else {
            return ResultModel.ok(ExceptionEnum.FIND_DATA_ISEMPTY.getMsg());
        }
    }
}
