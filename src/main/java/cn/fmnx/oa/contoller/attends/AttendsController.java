package cn.fmnx.oa.contoller.attends;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.utils.GetUserInfoUtils;
import cn.fmnx.oa.contoller.attends.dto.SignInDTO;
import cn.fmnx.oa.entity.attends.Attends;
import cn.fmnx.oa.service.attendsService.impl.AttendsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName AttendsController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/3/1
 * @Version V1.0
 **/
@RestController
@Api(tags = "考勤管理相关接口")
public class AttendsController {
    @Autowired
    private AttendsServiceImpl attendsService;
    @ApiOperation(value = "员工签到的接口")
    @PostMapping("/signIn")
    public ResultModel signIn(@RequestBody SignInDTO signInDTO, HttpServletRequest request){
        Attends attends = new Attends();
        //获取登录的ip地址
        String ipAddr = GetUserInfoUtils.getIpAddr(request);
        //获得当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fullDate = sdf.format(date);
        //获取当前的时间时分秒
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        String hmsDate = sdf2.format(date);
        //获取星期
        SimpleDateFormat sdf3 = new SimpleDateFormat("E");
        String week = sdf3.format(date);
        //设置上班的打卡时间(九点十分后打卡为迟到typeId=8(上班),statusId=11(迟到))
        String star = "09:10;00";
        //设置下班的打卡时间(六点之前打卡为早退typeId=9(下班),statusId=12(早退))
        String end = "18:00:00";
        //每个人每天知有两条记录(上班打卡，下班打卡)根据用户的id和时间查看今天的记录0:代表没有打卡，1:代表一条打卡记录
        //获取今天打卡时间
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        String ymdDate = ymd.format(date);
        //设置打卡ip
        attends.setAttendsIp(ipAddr);
        //设置打卡完整时间时间
        attends.setAttendsTime(date);
        //设置打卡的时分秒时间
        attends.setAttendHmtime(hmsDate);
        //设置打卡的用户
        attends.setAttendsUserId(signInDTO.getAttendsUserId());
        //设置打卡的星期
        attends.setWeekOfday(week);
        Integer count = attendsService.findSignInCount(signInDTO.getAttendsUserId(),ymdDate);
        switch (week){
            case "星期一":
            case "星期二":
            case "星期三":
            case "星期四":
            case "星期五":
                //判断上班
                if (count==0){
                    //上班打卡时间（最早08:00:00开始打卡）已过
                    if (hmsDate.compareTo(star)>0){
                        //设置typeId为上班
                        attends.setTypeId(8L);
                        //设置statusId为迟到
                        attends.setStatusId(11L);
                    }
                    //上班打卡时间过早
                    if (hmsDate.compareTo("08:00:00")<0){
                        throw new OaException(ExceptionEnum.NOT_SUPPORT_SIGNIN_TIME);
                    }
                    //正常的上班打卡时间
                    if (hmsDate.compareTo("08:00:00")>0 && hmsDate.compareTo(star)<0){
                        //设置typeId为上班
                        attends.setTypeId(8L);
                        //设置statusId为正常
                        attends.setStatusId(10L);
                    }
                }else if (count==1){
                    //下班

                }else {
                    throw new OaException(ExceptionEnum.SIGNIN_TODAY_FULL);
                }

                break;
            case "星期六":
            case "星期日":
                throw new OaException(ExceptionEnum.NO_WORK_ON_WEEKENDS);
        }
        return ResultModel.ok(ipAddr);
    }

}
