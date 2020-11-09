package cn.fmnx.oa.contoller.attends.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName AddAttendsDTO
 * @Description: TODO
 * @Author gmf
 * @Date 2020/3/1
 * @Version V1.0
 **/
@Data
@ApiModel(description = "考勤的数据对象")
public class AddAttendsDTO implements Serializable {
    private static final long serialVersionUID = -3906705529664392115L;
    @ApiModelProperty(name = "attendsId",value = "考勤表主键id")
    private Long attendsId;
    /**
     * //类型id
     */
    @ApiModelProperty(name = "typeId",value = "类型id(8=上班,9=下班,46=请假,47=出差)")
    private Long typeId;
    /**
     * //状态id
     */
    @ApiModelProperty(name = "statusId",value = "状态id(10=正常,11=迟到,12=早退,13=旷工)")
    private Long statusId;
    /**
     * //考勤时间
     */
    @ApiModelProperty(name = "attendsTime",value = "考勤时间",example = "2017-09-27 08:16:36")
    private Date attendsTime;
    /**
     * //考勤时分
     */
    @ApiModelProperty(name = "attendHmtime",value = "考勤时分",example = "08:16")
    private String attendHmtime;
    /**
     * //星期几
     */
    @ApiModelProperty(name = "weekOfday",value = "星期几")
    private String weekOfday;
    /**
     * //登陆ip
     */
    @ApiModelProperty(name = "attendsIp",value = "登陆的ip地址")
    private String attendsIp;
    /**
     * //考勤备注
     */
    @ApiModelProperty(name = "attendsRemark",value = "考勤的备注")
    private String attendsRemark;
    /**
     * //请假开始时间
     */
    @ApiModelProperty(name = "holidayStart",value = "请假开始时间")
    private Date holidayStart;
    /**
     * //请假时间（天数）
     */
    @ApiModelProperty(name = "holidayDays",value = "请假时间天数")
    private Double holidayDays;
    /**
     * 用户id
     */
    @ApiModelProperty(name = "attendsUserId",value = "用户id")
    private Long attendsUserId;
}
