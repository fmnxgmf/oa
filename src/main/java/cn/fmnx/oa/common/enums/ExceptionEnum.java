package cn.fmnx.oa.common.enums;

import lombok.*;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2019/12/30 14:16
 * @version:
 * @modified By:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    NOT_FOUND_USER(400,"[用户不存在]"),
    FAIL_TOKEN(400,"[TOKEN解析失败]"),
    NOT_FOUND_USER_ROLE_ID(400,"[用户角色ID不存在]"),
    INVALID_USER_DATA_TYPE(400,"[用户数据类型无效！]" ),
    INVALID_VERIFY_CODE(400,"[图片验证码失效或校验失败！]" ),
    INVALID_USERNAME_PASSWORD(400,"[用户名或密码错误！]" ),
    CREATE_TOKEN_ERROR(500,"[用户凭证生成失败！]" ),
    NO_AUTHORIZED_PRO(401,"[TOKEN生成失败！]" ),
    CREATE_ORDER_ERROR(500,"[创建订单失败！]" ),
    STOCK_NOT_ENOUGH(400,"[库存不足！]" ),
    ORDER_NOT_FOUND(404,"[订单不存在！]" ),
    ORDER_DETAIL_NOT_FOUNT(404,"[订单详情不存在！]" ),
    ORDER_STATUS_NOT_FOUND(404,"[订单状态不存在！]" ),
    WX_PAY_ORDER_FAIL(500,"[微信下单失败！]" ),
    ORDER_STATUS_ERROE(500,"[订单状态异常！]" ),
    INVALID_ORDER_PARAM(400,"[订单参数异常！]" ),
    UPDATE_ORDER_STATUS_ERROR(400,"[订单状态更新失败！]" );;
    private int code;
    private String msg;

}
