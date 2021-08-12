package com.forsea.pojo;

import com.forsea.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 采用链式编程
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("返回结果统一封装")
public class Result {
    @ApiModelProperty("返回码")
    private Integer code;
    @ApiModelProperty("返回信息")
    private String message;
    @ApiModelProperty("返回数据")
    private Object data;

    /**
     * 操作成功
     * @return
     */
    public static Result success(){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * 操作失败
     * @return
     */
    public static Result fail(){
        Result result = new Result();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(ResultCode.FAIL.getMessage());
        return result;
    }

    /**
     * 设置code
     * @param code
     * @return
     */
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 设置message
     * @param message
     * @return
     */
    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 设置data
     * @param data
     * @return
     */
    public Result data(Object data){
        this.setData(data);
        return this;
    }
}
