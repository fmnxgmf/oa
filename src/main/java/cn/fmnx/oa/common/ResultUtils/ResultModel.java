package cn.fmnx.oa.common.ResultUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/23 15:51
 * @version:
 * @modified By:
 */
@Data
@ApiModel(description = "后端返回数据结构的模板对象")
public class ResultModel<T> implements Serializable {
    //定义jackson对象
    private static final ObjectMapper mapper = new ObjectMapper();

    //响应业务状态码
    @ApiModelProperty(value = "返回的状态码",example = "200")
    private Integer code;

    // 响应消息
    @ApiModelProperty(value = "返回响应消息")
    private String msg;

    /**
     * 返回数据量
     */
    @ApiModelProperty(value = "返回的数据量")
    private Integer count;


    // 返回内容
    @ApiModelProperty(value = "返回的内容")
    private T content;

    public ResultModel(){}

    public ResultModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.content = null;
    }

    public ResultModel(Integer code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public ResultModel(ResultStatus status, T content){
        this.code = status.getCode();
        this.msg = status.getMessage();
        this.content = content;
    }
    public ResultModel(ResultStatus status, String message) {
        this.code = status.getCode();
        this.msg = message;
    }
    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.msg = status.getMessage();
        this.content = null;
    }
    public ResultModel(ResultStatus status, T content, Integer count) {
        this.code = status.getCode();
        this.msg = status.getMessage();
        this.content = content;
        this.count = count;
    }
    public static ResultModel ok(Object content, Integer count) {
        return new ResultModel(ResultStatus.SUCCESS, content, count);
    }
    public static ResultModel ok() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    public static ResultModel isOk() {
        return new ResultModel(ResultStatus.ISOK);
    }

    public static ResultModel ok(Object content) {
        return new ResultModel(ResultStatus.SUCCESS, content);
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }

    public static ResultModel error(String message) {
        return new ResultModel(-100, message);
    }
}
