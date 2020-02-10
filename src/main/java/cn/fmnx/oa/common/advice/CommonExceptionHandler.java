package cn.fmnx.oa.common.advice;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.exception.ExceptionResult;
import cn.fmnx.oa.common.exception.OaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName CommonExceptionHandler
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/9
 * @Version V1.0
 **/

/**
 * /通过@ControllerAdvice注解可以将对于控制器的全局配置放在同一个位置。
 * //　　注解了@Controller的类的方法可以使用@ExceptionHandler、@InitBinder、@ModelAttribute注解到方法上。
 * //　　@ControllerAdvice注解将作用在所有注解了@RequestMapping的控制器的方法上
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(OaException.class)
    public ResponseEntity<ExceptionResult> handleException(OaException e){
        return ResponseEntity.status(e.getExceptionEnum().getCode())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }
}
