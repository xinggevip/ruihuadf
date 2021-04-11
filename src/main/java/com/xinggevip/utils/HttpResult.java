package com.xinggevip.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xinggevip.enunm.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;


@JsonInclude(value = JsonInclude.Include.NON_NULL)  // 属性值为空的属性不返回给前端
@Slf4j
public class HttpResult<T> implements Serializable {
    /**
     * 是否响应成功
     */
    private Boolean success;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 错误信息
     */
    private String message;

    // 构造器开始
    /**
     * 无参构造器(构造器私有，外部不可以直接创建)
     */
    private HttpResult() {
        this.code = 200;
        this.success = true;
    }

    /**
     * 有参构造器
     * @param obj
     */
    public HttpResult(T obj) {
        this.code = 200;
        this.data = obj;
        this.success = true;
    }

    public HttpResult(ResultCodeEnum resultCode, T obj, Boolean success) {
        this.success = success;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = obj;
    }

    /**
     * 有参构造器
     * @param resultCode
     */
    private HttpResult(ResultCodeEnum resultCode) {
        this.success = false;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    private HttpResult(ResultCodeEnum resultCode,String message) {
        this.success = false;
        this.code = resultCode.getCode();
        this.message = message;
    }

    private HttpResult(ResultCodeEnum resultCode, Boolean success) {
        this.success = success;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    // 构造器结束

    /**
     * 通用返回成功（没有返回结果）
     * @param <T>
     * @return
     */
    public static<T> HttpResult<T> success(){
        return new HttpResult();
    }

    // 带消息返回成功
    public static<T> HttpResult<T> success(ResultCodeEnum resultCode){
        return new HttpResult<>(resultCode,Boolean.TRUE);
    }

    // 自定义返回成功还是失败，带消息
    public static<T> HttpResult<T> success(ResultCodeEnum resultCode, Boolean success){
        return new HttpResult<>(resultCode, success);
    }



    /**
     * 返回成功（有返回结果）
     * @param data
     * @param <T>
     * @return
     */
    public static<T> HttpResult<T> success(T data){
        return new HttpResult<T>(data);
    }

    /**
     * 通用返回失败
     * @param resultCode
     * @param <T>
     * @return
     */
    public static<T> HttpResult<T> failure(ResultCodeEnum resultCode){
        return  new HttpResult<T>(resultCode);
    }

    public static<T> HttpResult<T> failure(ResultCodeEnum resultCode,String message){
        return  new HttpResult<T>(resultCode,message);
    }

    public static<T> HttpResult<T> failure(ResultCodeEnum resultCode, BindingResult bindingResult){
        String message = "";
        String separator = System.getProperty("line.separator");
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            message += fieldError.getField() + " " + fieldError.getDefaultMessage() + separator;
        }
        log.info("message = {}",message);
        return  new HttpResult<T>(resultCode,message);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "success=" + success +
                ", code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }


}
