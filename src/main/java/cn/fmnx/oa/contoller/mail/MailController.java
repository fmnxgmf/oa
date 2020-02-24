package cn.fmnx.oa.contoller.mail;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.dto.AddMailAcountDTO;
import cn.fmnx.oa.contoller.mail.dto.PushMailDTO;
import cn.fmnx.oa.contoller.mail.vo.*;
import cn.fmnx.oa.service.mailService.MailNumService;
import cn.fmnx.oa.service.mailService.impl.InmaillistServiceImpl;
import cn.fmnx.oa.service.mailService.impl.MailNumServiceImpl;
import cn.fmnx.oa.service.mailService.impl.MailreciverServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private MailreciverServiceImpl mailreciverService;
    @Autowired
    private InmaillistServiceImpl inmaillistService;
    /**
     * @MethodName: addMailNum
     * @Description: 新增内部邮箱的接口
     * @Param: [addMailAcountDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/22
    **/
    @ApiOperation(value = "新增外部邮箱的接口")
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
    @ApiOperation(value = "内部邮箱展示的数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int"),
            @ApiImplicitParam(name = "userId",value = "用户的id",required = true)
    })
    @GetMapping("/showMail")
    public ResultModel<PageResult<MailAccountsVO>> showAllMailAccountByUser(@RequestParam("userId") Long userId,
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
    /**
     * @MethodName: findMailNumVO
     * @Description: 查询该用户外部邮箱的接口
     * @Param: [userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.MailNumVO>
     * @Author: gmf
     * @Date: 2020/2/24
    **/
    @ApiOperation(value = "查询该用户外部邮箱的数据用于下拉框")
    @GetMapping("/findMailNum")
    public ResultModel<MailNumVO> findMailNumVO(@RequestParam("userId")Long userId){
        List<MailNumVO> mailNumVOS = mailNumService.findMailNum(userId);
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(mailNumVOS)){
            map.put("mailNumVOS",mailNumVOS);
            return ResultModel.ok(map,mailNumVOS.size());
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: pushMail
     * @Description: 发送邮件的接口
     * @Param: [pushMailDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/24
    **/
    @ApiOperation(value = "发送内部邮件的接口")
    @PostMapping("/pushMail")
    public ResultModel pushMail(@RequestBody PushMailDTO pushMailDTO){
        boolean b = inmaillistService.pushMail(pushMailDTO);
        if (b){
            return ResultModel.ok("邮件发送成功");
        }
        return ResultModel.error("邮件发送失败");

    }
    /**
     * @MethodName: findInMailListType
     * @Description: 邮件类型查询接口
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.MailTypeVO>
     * @Author: gmf
     * @Date: 2020/2/24
    **/
    @ApiOperation(value = "邮件类型查询接口(通知，公告，邮件)")
    @GetMapping("/findInMailListType")
    public ResultModel<MailTypeVO> findInMailListType(){
        List<MailTypeVO> mailTypeVOS = inmaillistService.findInMailListType();
        Map map = new HashMap(2);
        if(! CollectionUtils.isEmpty(mailTypeVOS)){
            map.put("mailTypeVOS",mailTypeVOS);
            return ResultModel.ok(map,mailTypeVOS.size());
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: findMailStatus
     * @Description: 查询邮件的状态接口(一般，紧急，重要)
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.MailStatusVO>
     * @Author: gmf
     * @Date: 2020/2/24
    **/
    @ApiOperation(value = "查询邮件的状态接口(20=一般，22=紧急，21=重要)")
    @GetMapping("/findMailStatus")
    public ResultModel<MailStatusVO> findMailStatus(){
        List<MailStatusVO> mailStatusVOS = inmaillistService.findMailStatus();
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(mailStatusVOS)){
            map.put("mailStatusVOS",mailStatusVOS);
            return ResultModel.ok(map,mailStatusVOS.size());
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: findMailNumType
     * @Description: 查询邮箱类型(1=系统邮件，2=公司邮件)
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.MailNumTypeVO>
     * @Author: gmf
     * @Date: 2020/2/24
    **/
    @ApiOperation(value = "查询邮箱类型(1=系统邮件，2=公司邮件)[用于添加外部邮箱的类型下拉框]")
    @GetMapping("/findMailNumType")
    public ResultModel<MailNumTypeVO> findMailNumType(){
      List<MailNumTypeVO> mailNumTypeVOS = mailNumService.findMailNumType();
      Map map = new HashMap(2);
      if (!CollectionUtils.isEmpty(mailNumTypeVOS)){
          map.put("mailNumTypeVOS",mailNumTypeVOS);
          return ResultModel.ok(map,mailNumTypeVOS.size());
      }else {
          throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
      }
    }
    /**
     * @MethodName: findMailNumStatus
     * @Description: 邮箱的状态(1=有效，2=无效)
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.MailNumStatusVO>
     * @Author: gmf
     * @Date: 2020/2/24
    **/
    @ApiOperation(value = "邮箱的状态(1=有效，2=无效)[用于添加外部邮箱的状态下拉框]")
    @GetMapping("/findMailNumStatus")
    public ResultModel<MailNumStatusVO> findMailNumStatus(){
        List<MailNumStatusVO> mailNumStatusVOS = mailNumService.findMailNumStatus();
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(mailNumStatusVOS)){
            map.put("mailNumStatusVOS",mailNumStatusVOS);
            return ResultModel.ok(map,mailNumStatusVOS.size());
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: findMailUsers
     * @Description: 通讯录
     * @Param: [userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.MailBooksVO>
     * @Author: gmf
     * @Date: 2020/2/24
    **/
    @ApiOperation(value = "内部邮箱发送时禁止用户自主手动添加接收者，由此接口返回通讯录")
    @ApiImplicitParam(name = "userId",value = "该用户id")
    @GetMapping("/findMailBook")
    public ResultModel<MailBooksVO> findMailUsers(@RequestParam("userId")Long userId){
        List<MailBooksVO> mailBooksVOS = mailNumService.findMailUsers(userId);
        Map map = new HashMap(2);
        if (!CollectionUtils.isEmpty(mailBooksVOS)){
            map.put("mailBooksVOS",mailBooksVOS);
            return ResultModel.ok(map,mailBooksVOS.size());
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: showInBox
     * @Description: 展示收件箱的数据的接口
     * @Param: []
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.common.page.PageResult<cn.fmnx.oa.contoller.mail.vo.MailInBoxVO>>
     * @Author: gmf
     * @Date: 2020/2/24
    **/
    @ApiOperation(value = "展示收件箱的数据的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int"),
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    @GetMapping("/showMailInBox")
    public ResultModel<PageResult<MailInBoxVO>> showInBox(@RequestParam("pageNum")Integer pageNum,
                                                          @RequestParam("pageSize")Integer pageSize,
                                                          @RequestParam("userId")Long userId){
        PageDTO pageDTO;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }
        PageResult<MailInBoxVO> pageResult = mailreciverService.showMailInBox(userId,pageDTO);
        return ResultModel.ok();
    }
}
