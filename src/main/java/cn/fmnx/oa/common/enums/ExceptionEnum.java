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
    FIND_DATA_ISEMPTY(200,"[查询数据为空]"),
    NO_WORK_ON_WEEKENDS(200,"[双休不上班!]"),
    SIGNIN_TODAY_FULL(100,"[今日打卡已完成!]"),
    NOT_SUPPORT_SIGNIN_TIME(100,"[当前时间暂不支持打卡]"),
    PUSH_EXTERNAL_MAIL_FAIL(500,"[外部邮件发送失败]"),
    MAIL_BEACON_STAR_FAIL(500,"[邮件标星无效]"),
    MAIL_BEACON_READY_FAIL(500,"[邮件标记为已读无效]"),
    ADD_MAIL_ACCOUNT_FAIL(500,"[新增邮箱账户无效]"),
    FIND_DEPT_MANAGER_FAIL(200,"[查询部门经理无效]"),
    USER_LOGIN_RECORD_FAIL(500,"[用户登录记录保存无效]"),
    SET_ROLE_POWER_ERROR(500,"[设置权限无效]"),
    DELETE_ROLE_POWER_ERROR(500,"[删除权限无效]"),
    CONTENT_TYPE_ERROE(400,"上传文件类型无效！" ),
    INVALID_IMAGE_ERROE(400,"文件内容无效！" ),
    UPLOAD_FAILED_ERROE(400, "文件上传失败！"),
    ECHO_DATA_FAIL(500,"[回显数据失败!]"),
    NOT_FOUND_USER(400,"[用户不存在!]"),
    MOVE_MENU_FAIL(500,"[菜单移动无效]"),
    FIND_ONEMENU_BYID_ERROR(500,"[查找单个菜单数据无效!]"),
    NOT_FOUND_PARENT_MENUS(500,"[查找所有父级菜单无效]"),
    FAIL_ADD_MENU(500,"[新增菜单数据无效！]"),
    FAIL_TOKEN(400,"[TOKEN解析失败!]"),
    NOT_FOUND_USER_ROLE_ID(400,"[用户角色ID不存在]"),
    INVALID_USER_DATA_TYPE(400,"[用户数据类型无效！]" ),
    INVALID_VERIFY_CODE(400,"[图片验证码失效或校验失败！]" ),
    INVALID_USERNAME_PASSWORD(400,"[用户名或密码错误！]" ),
    CREATE_TOKEN_ERROR(500,"[用户凭证生成失败！]" ),
    CREATE_DATA_LIST_ERROR(500,"[添加数据失败!]"),
    UPDATE_DATA_LIST_ERROR(500,"[修改数据失败!]"),
    DELETE_DATA_LIST_ERROR(500,"[删除数据失败!]"),
    FAILD_FIND_MENUS(500,"[查找所有菜单数据失败!]"),
    FAILD_FIND_STATUS(500,"[查找所有状态数据失败!]"),
    NO_AUTHORIZED_PRO(401,"[TOKEN生成失败！]" )
     ;
    private int code;
    private String msg;

}
