package com.xinggevip.enunm;

public enum ResultCodeEnum {
    /*** 通用部分 100 - 599***/
    // 成功请求
    SUCCESS(200, "successful"),
    // 注册成功
    REG_SUCCESS(200, "添加成功"),
    // 通用添加成功
    ADD_SUCCESS(201, "添加成功"),
    // 通用更新成功
    UPDATE_SUCCESS(202, "更新成功"),
    // 通用更新成功
    HANDLE_SUCCESS(202, "操作成功"),
    // 通用删除成功
    DELETE_SUCCESS(202, "删除成功"),


    // 重定向
    REDIRECT(301, "redirect"),

    // 资源未找到
    NOT_FOUND(404, "not found"),
    // 需要登录
    NEED_LOGIN(405,"需要登录"),
    // 参数错误
    PARAM_ERROR(406,"参数错误"),
    // 登录失败
    LOGIN_ERROR(407,"手机号或密码错误，登录失败"),
    // 登录失败
    ADD_ERROR(408,"添加失败"),
    // 更新失败
    UPDATE_ERROR(409,"更新失败"),
    // 更新失败
    MONEY_NOT_ENOUGH_ERROR(410,"余额不足"),
    // 操作失败
    HANDLE_ERROR(411,"操作失败"),
    // 删除失败
    DELETE_ERROR(412, "删除失败"),
    // 登录失败
    REG_ERROR(413,"注册失败"),
    // 登录失败
    REG_ERRORTWO(414,"注册失败，该手机号已注册！"),

    // 服务器错误
    SERVER_ERROR(500,"server error"),





    /*** 这里可以根据不同模块用不同的区级分开错误码，例如:  ***/

    // 1000～1999 区间表示用户模块错误
    // 2000～2999 区间表示订单模块错误
    // 3000～3999 区间表示商品模块错误
    // 。。。

    ;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
