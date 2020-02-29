package cn.fmnx.oa.contoller.mail;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.page.PageDTO;
import cn.fmnx.oa.common.page.PageResult;
import cn.fmnx.oa.contoller.mail.dto.AddMailAcountDTO;
import cn.fmnx.oa.contoller.mail.dto.PushExternalMailDTO;
import cn.fmnx.oa.contoller.mail.dto.PushMailDTO;
import cn.fmnx.oa.contoller.mail.vo.*;
import cn.fmnx.oa.service.mailService.MailNumService;
import cn.fmnx.oa.service.mailService.impl.InmaillistServiceImpl;
import cn.fmnx.oa.service.mailService.impl.MailNumServiceImpl;
import cn.fmnx.oa.service.mailService.impl.MailreciverServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
    @ApiOperation(value = "新增外部邮箱的接口,(注:外部邮箱暂只支持qq,163邮箱)")
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
    public ResultModel<PageResult<MailAccountsVO>> showAllMailAccountLike(@RequestParam("userId") Long userId,
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
    @ApiOperation(value = "展示收件箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true)
    })
    @GetMapping("/showMailInBox")
    public ResultModel<PageResult<MailInBoxVO>> showInBox(@RequestParam(value = "pageNum",required = false)Integer pageNum,
                                                          @RequestParam(value = "pageSize",required = false)Integer pageSize,
                                                          @RequestParam("userId")Long userId){
        PageDTO pageDTO;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }

        PageResult<MailInBoxVO> pageResult = mailreciverService.showMailInBox(userId,pageDTO);
        if (!CollectionUtils.isEmpty(pageResult.getItems())){
            return ResultModel.ok(pageResult);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }

    /**
     * @MethodName: deleteIds
     * @Description: 根据邮件id批量删除收件箱邮件
     * @Param: [mailIds]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/25
    **/
    @ApiOperation(value = "根据邮件id批量删除收件箱邮件")
    @DeleteMapping("/deleteMailInBox")
    public ResultModel deleteIds(@RequestBody @ApiParam(value = "该参数是List集合类型，并非数组",required = true) List<Long> mailIds){
        boolean flag = mailNumService.deletemailInBox(mailIds);
        if (flag){
            return ResultModel.ok("收件箱邮箱删除成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: setMailInBoxStar
     * @Description: 邮件表星操作
     * @Param: [mailIds, star]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/25
    **/
    @ApiOperation(value = "根据邮件id给收件箱邮件批量标星，同时需要转递star值来判断是标星还是取消标星")
    @PutMapping("/setMailInBoxStar")
    public ResultModel setMailInBoxStar(@RequestBody @ApiParam(value = "该参数是List<Map<mailId,star>>集合类型的键值对，键为该邮箱id，值是该星标star的值",
            required = true) List<Map<Integer,Integer>> mailIds){
        boolean flag = mailNumService.setMailInBoxStar(mailIds);
        if (flag){
            return ResultModel.ok("邮件标星成功");
        }else {
            throw new OaException(ExceptionEnum.MAIL_BEACON_STAR_FAIL);
        }
    }
    /**
     * @MethodName: setMailInBoxRead
     * @Description: 收件箱标记邮件是否已读
     * @Param: [mailIds]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/25
    **/
    @ApiOperation(value = "收件箱批量标记邮件是否已读")
    @PutMapping("/setMailInBoxRead")
    public ResultModel setMailInBoxRead(@RequestBody @ApiParam(value = "该参数是List<Map<mailId,star>>集合类型的键值对，键为该邮箱id，职位read的值",
            required = true)List<Map<Integer,Integer>>mailIds){
        boolean flag = mailNumService.setMailInBoxRead(mailIds);
        if (flag){
            return ResultModel.ok("标记邮件已读成功");
        }else {
            throw new OaException(ExceptionEnum.MAIL_BEACON_READY_FAIL);
        }
    }
    /**
     * @MethodName: findMailInBoxByLike
     * @Description: 模糊查询
     * @Param: [condition, pageNum, pageSize]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.common.page.PageResult<cn.fmnx.oa.contoller.mail.vo.MailInBoxVO>>
     * @Author: gmf
     * @Date: 2020/2/25
    **/
    @ApiOperation(value = "收件箱根据类型，状态，主题，发件人模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition",value = "查询的条件",required = false),
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true)
    })
    @GetMapping("/findMailInBoxLike")
    public ResultModel<PageResult<MailInBoxVO>> findMailInBoxByLike(@RequestParam(value = "condition",required = false) String condition,
                                                                    @RequestParam("userId")Long userId,
                                                                    @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                                    @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }
        PageResult<MailInBoxVO> pageResult = mailreciverService.findMailInBoxByLike(condition,pageDTO,userId);
        if (!CollectionUtils.isEmpty(pageResult.getItems())){
            return ResultModel.ok(pageResult);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: findOneMainInBoxById
     * @Description: 查询收件箱某封邮件详情
     * @Param: [mailId, userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.OneMailInBoxInfoVO>
     * @Author: gmf
     * @Date: 2020/2/26
    **/
    @ApiOperation(value = "查询收件箱某封邮件详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mailId",value = "该邮件的id值",required = true),
            @ApiImplicitParam(name = "userId",value = "该用户id值",required = true)
    })
    @GetMapping("/findOneMailInBox")
    public ResultModel<OneMailInBoxInfoVO> findOneMainInBoxById(@RequestParam("mailId")Long mailId,@RequestParam("userId")Long userId){
        OneMailInBoxInfoVO oneMailInBoxInfoVO = mailreciverService.findOneMailInBox(mailId,userId);
        Map map = new HashMap(2);
        if (!StringUtils.isEmpty(oneMailInBoxInfoVO)){
            map.put("oneMailInBoxInfoVO",oneMailInBoxInfoVO);
            return ResultModel.ok(map,1);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: replyMail
     * @Description: 邮件回复的接口
     * @Param: [mailId, userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.ReplyMailInboxVO>
     * @Author: gmf
     * @Date: 2020/2/26
    **/
    @ApiOperation(value = "邮件回复的接口，收件人由后端返回，即原发件人就是现收件人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mailId",value = "原邮件id",required = true),
            @ApiImplicitParam(name = "userId",value = "自己用户id",required = true)
    })
    @GetMapping("/replyMail")
    public ResultModel<ReplyMailInboxVO> replyMail(@RequestParam("mailId")Long mailId,@RequestParam("userId")Long userId){
        ReplyMailInboxVO replyMailInboxVO = mailreciverService.replyMail(userId,mailId);
        Map map = new HashMap(2);
        if (!StringUtils.isEmpty(replyMailInboxVO)){
            map.put("replyMailInboxVO",replyMailInboxVO);
            return ResultModel.ok(map,1);
        }else {
            throw new OaException(ExceptionEnum.ECHO_DATA_FAIL);
        }
    }
    /**
     * @MethodName: showOutBox
     * @Description: 发件箱
     * @Param: [pageNum, pageSize, userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/24
     **/
    @ApiOperation(value = "展示该用户的发件箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true)
    })
    @GetMapping("/showOutBox")
    public ResultModel<MailOutBoxVO> showOutBox(@RequestParam(value = "pageNum",required = false)Integer pageNum,
                                                @RequestParam(value = "pageSize",required = false)Integer pageSize,
                                                @RequestParam(value = "userId")Long userId){
        PageDTO pageDTO;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }

        PageResult<MailOutBoxVO>  pageResult = inmaillistService.showMailOutBox(userId,pageDTO);
        if (!CollectionUtils.isEmpty(pageResult.getItems())){
            return ResultModel.ok(pageResult);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: deleteMailOutBox
     * @Description: 根据id批量删除发件箱邮件
     * @Param: [mailIds]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/26
    **/
    @ApiOperation(value = "根据id批量删除发件箱邮件")
    @DeleteMapping("/deleteMailOutBox")
    public ResultModel deleteMailOutBox(@RequestBody @ApiParam(value = "该参数是List集合类型，并非数组",required = true) List<Long> mailIds){
        boolean flag = inmaillistService.deleteMailOutBox(mailIds);
        if (flag){
            return ResultModel.ok("发件箱邮件删除成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: setMailOutBoxStar
     * @Description: 给发件箱的邮件批量标/取消标星
     * @Param: [mailIds]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/26
    **/
    @ApiOperation(value = "给发件箱的邮件批量标/取消标星")
    @PutMapping("/setMailOutBoxStar")
    public ResultModel setMailOutBoxStar(@RequestBody @ApiParam(value = "该参数是List<Map<mailId,star>>集合类型的键值对，键为该邮箱id，值是该星标star的值",
            required = true)List<Map<Integer,Integer>> mailIds){
        boolean flag = inmaillistService.setMailOutBoxStar(mailIds);
        if (flag){
            return ResultModel.ok("批量标星成功");
        }else {
            throw new OaException(ExceptionEnum.MAIL_BEACON_STAR_FAIL);
        }
    }
    /**
     * @MethodName: showMailOutBoxByLike
     * @Description: 发件箱模糊查询，类型，状态，主题，收件人
     * @Param: [condition, userId, pageNum, pageSize]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.common.page.PageResult<cn.fmnx.oa.contoller.mail.vo.MailOutBoxVO>>
     * @Author: gmf
     * @Date: 2020/2/26
    **/
    @ApiOperation(value = "发件箱模糊查询，类型，状态，主题，收件人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition",value = "查询的条件",required = false),
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true)
    })
    @GetMapping("/findMailOutBoxLike")
    public ResultModel<PageResult<MailOutBoxVO>> showMailOutBoxByLike(@RequestParam(value = "condition",required = false) String condition,
                                                                      @RequestParam("userId")Long userId,
                                                                      @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                                      @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO;
        if (pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }

       PageResult<MailOutBoxVO> mailOutBoxVOPageResult = inmaillistService.findMailOutBoxByLike(condition,pageDTO,userId);
        if (!CollectionUtils.isEmpty(mailOutBoxVOPageResult.getItems())){
            return ResultModel.ok(mailOutBoxVOPageResult);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: DraftsBox
     * @Description: 草稿箱查询
     * @Param: [pushMailDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.DraftsBoxVO>
     * @Author: gmf
     * @Date: 2020/2/27
    **/
    @ApiOperation(value = "查看草稿箱相关的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false,dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false,dataType = "int"),
    })
    @GetMapping("/DraftsBox")
    public ResultModel<PageResult<DraftsBoxVO>> DraftsBox(@RequestParam("userId")@ApiParam(value = "用户id",required = true) Long userId,
                                                          @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                          @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }

        PageResult<DraftsBoxVO> pageResult = inmaillistService.findAllDraftsBoxVO(userId,pageDTO);
        if (!CollectionUtils.isEmpty(pageResult.getItems())){
            return ResultModel.ok(pageResult);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: deleteDraftsBox
     * @Description: 根据mailId批量删除草稿箱
     * @Param: [mailIds]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/27
    **/
    @ApiOperation(value = "根据mailId批量删除草稿箱")
    @DeleteMapping("/deleteDraftsBox")
    public ResultModel deleteDraftsBox(@RequestBody List<Long> mailIds){
        boolean flag = inmaillistService.deleteDraftsBox(mailIds);
        if (flag){
            return ResultModel.ok("草稿箱删除成功");
        }else {
            throw new OaException(ExceptionEnum.DELETE_DATA_LIST_ERROR);
        }
    }
    /**
     * @MethodName: showOneDraftsBox
     * @Description: 查询某个草稿箱的详情
     * @Param: [mailId, userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/28
    **/
    @ApiOperation(value = "根据mailId查看草稿箱的详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mailId",value = "草稿箱的id值",required = true),
            @ApiImplicitParam(name = "userId",value = "用户的id值",required = true)
    })
    @GetMapping("/showOneDraftsBox")
    public ResultModel<DraftsBoxVO> showOneDraftsBox(@RequestParam("mailId")Long mailId,@RequestParam("userId")Long userId){
      DraftsBoxVO draftsBoxVO = inmaillistService.findOneDraftsBox(mailId,userId);
      Map map = new HashMap(2);
      if (!StringUtils.isEmpty(draftsBoxVO)){
          map.put("draftsBoxVO",draftsBoxVO);
          return ResultModel.ok(map,1);
      }else {
          throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
      }
    }
    /**
     * @MethodName: findDraftsBoxByLike
     * @Description: 模糊查询草稿箱
     * @Param: [condition, userId, pageNum, pageSize]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.common.page.PageResult<cn.fmnx.oa.contoller.mail.vo.DraftsBoxVO>>
     * @Author: gmf
     * @Date: 2020/2/28
    **/

    @ApiOperation(value = "模糊查询草稿箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition",value = "查询的条件",required = false),
            @ApiImplicitParam(name = "pageNum",value = "分页页码",required = false),
            @ApiImplicitParam(name = "pageSize",value = "每页大小",required = false),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true)
    })
    @GetMapping("/showDraftsBoxByLike")
    public ResultModel<PageResult<DraftsBoxVO>> findDraftsBoxByLike(@RequestParam(value = "condition",required = false)String condition,
                                                                    @RequestParam("userId")Long userId,
                                                                    @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                                                    @RequestParam(value = "pageSize",required = false)Integer pageSize){
        PageDTO pageDTO ;
        if(pageNum !=null && pageSize !=null){
            pageDTO = new PageDTO(pageNum,pageSize);
        }else {
            pageDTO = new PageDTO(1,10);
        }

        PageResult<DraftsBoxVO> pageResult = inmaillistService.findOneDraftsBoxByLike(pageDTO,condition,userId);
        if (!CollectionUtils.isEmpty(pageResult.getItems())){
            return ResultModel.ok(pageResult);
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: showExternalMail
     * @Description: 查询某个用户的外部邮箱账户
     * @Param: [userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel<cn.fmnx.oa.contoller.mail.vo.ExternalMailVO>
     * @Author: gmf
     * @Date: 2020/2/29
    **/
    @ApiOperation(value = "查询某个用户的外部邮箱账户")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true)
    @GetMapping("/showExternalMail")
    public ResultModel<ExternalMailVO> showExternalMail(@RequestParam("userId")Long userId){
        List<ExternalMailVO> list = mailNumService.findExternalMail(userId);
        Map map = new HashMap();
        if (!CollectionUtils.isEmpty(list)){
            map.put("externalMail",list);
            return ResultModel.ok(map,list.size());
        }else {
            throw new OaException(ExceptionEnum.FIND_DATA_ISEMPTY);
        }
    }
    /**
     * @MethodName: pushExternalMail
     * @Description: 外部邮件的发送
     * @Param: [pushMailDTO]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/29
    **/
    @ApiOperation(value = "发送外部邮箱的接口")
    @PostMapping("/pushExternalMail")
    public ResultModel pushExternalMail(@RequestBody PushExternalMailDTO pushExternalMailDTO){
        boolean flag = mailNumService.pushExternalMail(pushExternalMailDTO);
        if (flag){
            return ResultModel.ok("外部邮件发送成功");
        }else {
            throw new OaException(ExceptionEnum.PUSH_EXTERNAL_MAIL_FAIL);
        }
    }
}
