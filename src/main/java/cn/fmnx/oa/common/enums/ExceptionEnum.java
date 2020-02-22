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
    ADD_MAIL_ACCOUNT_FAIL(500,"[新增邮箱账户失败]"),
    FIND_DEPT_MANAGER_FAIL(200,"[查询部门经理失败]"),
    USER_LOGIN_RECORD_FAIL(500,"[用户登录记录保存失败]"),
    SET_ROLE_POWER_ERROR(500,"[设置权限失败]"),
    DELETE_ROLE_POWER_ERROR(500,"[删除权限失败]"),
    CONTENT_TYPE_ERROE(400,"上传文件类型无效！" ),
    INVALID_IMAGE_ERROE(400,"文件内容无效！" ),
    UPLOAD_FAILED_ERROE(400, "文件上传失败！"),
    ECHO_DATA_FAIL(500,"[回显数据失败!]"),
    NOT_FOUND_USER(400,"[用户不存在!]"),
    MOVE_MENU_FAIL(500,"[菜单移动失败]"),
    FIND_ONEMENU_BYID_ERROR(500,"[查找单个菜单数据失败!]"),
    NOT_FOUND_PARENT_MENUS(500,"[查找所有父级菜单失败]"),
    FAIL_ADD_MENU(500,"[新增菜单数据失败！]"),
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
