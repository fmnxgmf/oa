package cn.fmnx.oa.common.exception;

import cn.fmnx.oa.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2019/12/30 11:20
 * @version:
 * @modified By:
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OaException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
