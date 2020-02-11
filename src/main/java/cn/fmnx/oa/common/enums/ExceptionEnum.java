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
    NOT_FOUND_USER(400,"[用户不存在!]"),
    FIND_ONEMENU_BYID_ERROR(500,"[查找单个菜单数据失败!]"),
    NOT_FOUND_PARENT_MENUS(500,"[查找所有父级菜单失败]"),
    FAIL_ADD_MENU(500,"[新增菜单数据失败！]"),
    FAIL_TOKEN(400,"[TOKEN解析失败!]"),
    NOT_FOUND_USER_ROLE_ID(400,"[用户角色ID不存在]"),
    INVALID_USER_DATA_TYPE(400,"[用户数据类型无效！]" ),
    INVALID_VERIFY_CODE(400,"[图片验证码失效或校验失败！]" ),
    INVALID_USERNAME_PASSWORD(400,"[用户名或密码错误！]" ),
    CREATE_TOKEN_ERROR(500,"[用户凭证生成失败！]" ),
    CREATE_TYPE_LIST_ERROR(500,"[添加类型管理数据失败!]"),
    UPDATE_TYPE_LIST_ERROR(500,"[修改类型管理数据失败!]"),
    DELETE_TYPE_LIST_ERROR(500,"[删除类型管理数据失败!]"),
    FAILD_FIND_MENUS(500,"[查找所有菜单数据失败!]"),
    NO_AUTHORIZED_PRO(401,"[TOKEN生成失败！]" )
     ;
    private int code;
    private String msg;

}
