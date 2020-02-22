package cn.fmnx.oa.contoller.mail;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.dto.AddMailAcountDTO;
import cn.fmnx.oa.contoller.mail.vo.MailAccountsVO;
import cn.fmnx.oa.service.mailService.MailNumService;
import cn.fmnx.oa.service.mailService.impl.MailNumServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MailController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/19
 * @Version V1.0
 **/
@RestController
@Api(tags = "邮件管理相关接口")
public class MailController {
    @Autowired
    private MailNumServiceImpl mailNumService;
    /**
     * @MethodName: addMailNum
     * @Description: 新增内部邮箱的接口
     * @Param: [addMailAcountDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/22
    **/
    @ApiOperation(value = "新增内部邮箱的接口")
    @PostMapping("/addMailAccount")
    public ResultModel addMailNum(@RequestBody @Validated AddMailAcountDTO addMailAcountDTO){
       boolean flag = mailNumService.addMailAccount(addMailAcountDTO);
       if (flag){
           return ResultModel.ok("新增邮箱成功!");
       }else {
           throw new OaException(ExceptionEnum.ADD_MAIL_ACCOUNT_FAIL);
       }

    }
    /**
     * @MethodName: showAllMailAccountByUser
     * @Description: 邮箱展示的数据
     * @Param: [UserId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.common.page.PageResult<cn.fmnx.oa.contoller.mail.vo.MailAccountsVO>>
     * @Author: gmf
     * @Date: 2020/2/22
    **/
    @ApiOperation(value = "邮箱展示的数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int"),
            @ApiImplicitParam(name = "userId",value = "用户的id",required = true)
    })
    @GetMapping("/showMail")
    public ResultModel<PageResult<MailAccountsVO>> showAllMailAccountByUser(@RequestParam("UserId") Long userId,
                                                                            @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                                            @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }

       PageResult<MailAccountsVO> pageResult = mailNumService.findAllMailByUserId(userId,pageDTO);
       if (!CollectionUtils.isEmpty(pageResult.getItems())){
           return ResultModel.ok(pageResult);
       }else {
           throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
       }
    }
    /**
     * @MethodName: showAllMailAccountLike
     * @Description: 邮箱展示的数据按账号名模糊查询
     * @Param: [userId, condition, pageNum, pageSize]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.common.page.PageResult<cn.fmnx.oa.contoller.mail.vo.MailAccountsVO>>
     * @Author: gmf
     * @Date: 2020/2/22
    **/
    @ApiOperation(value = "邮箱展示的数据按账号名模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int"),
            @ApiImplicitParam(name = "userId",value = "用户的id",required = true),
            @ApiImplicitParam(name = "condition",value = "模糊查询的条件")
    })
    @GetMapping("/showMailByLike")
    public ResultModel<PageResult<MailAccountsVO>> showAllMailAccountLike(@RequestParam("UserId") Long userId,
                                                                            @RequestParam("condition") String condition,
                                                                            @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                                            @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }
        PageResult<MailAccountsVO> pageResult = mailNumService.findAllMailByLike(userId,pageDTO,condition);
        if (!CollectionUtils.isEmpty(pageResult.getItems())){
            return ResultModel.ok(pageResult);
        }else {
            return ResultModel.ok(ExceptionEnum.FIND_DATA_ISEMPTY.getMsg());
        }
    }
    /**
     * @MethodName: updateMailAccount
     * @Description: 修改邮箱账户的接口
     * @Param: [mailAcountDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/22
    **/
    @ApiOperation(value = "修改邮箱账户的接口")
    @PutMapping("/updateMailAccount")
    public ResultModel updateMailAccount(@RequestBody AddMailAcountDTO mailAcountDTO){
         boolean flag = mailNumService.updateMailAccount(mailAcountDTO);
         if(flag){
             return ResultModel.ok("邮箱修改成功");
         }else {
             throw new OaException(ExceptionEnum.UPDATE_DATA_LIST_ERROR);
         }
    }
    /**
     * @MethodName: deleteMailAccount
     * @Description: 删除邮箱账号
     * @Param: [mailNumberId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/22
    **/
    @ApiOperation(value = "删除邮箱账号")
    @ApiImplicitParam(name = "mailNumberId",value = "该邮箱的id",required = true)
    @DeleteMapping("/deleteMailAccount")
    public ResultModel deleteMailAccount(@RequestParam Long mailNumberId){
       boolean flag = mailNumService.deleteMailAccount(mailNumberId);
       if (flag){
           return ResultModel.ok("该邮箱删除成功");
       }else {
           throw new OaException(ExceptionEnum.DELETE_DATA_LIST_ERROR);
       }
    }
}
