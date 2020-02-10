package cn.fmnx.oa.common.exception;

import cn.fmnx.oa.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2019/12/30 11:20
 * @version:
 * @modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;
    public ExceptionResult(ExceptionEnum em){
        this.status = em.getCode();
        this.message =  em.getMsg();
        this.timestamp =   System.currentTimeMillis();
    }
}
