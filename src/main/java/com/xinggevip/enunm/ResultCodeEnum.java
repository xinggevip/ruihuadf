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
    DAFEN_SUCCESS(202, "打分成功"),


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
    // 找不到此验证码
    NOFIND_CODE_ERRORTWO(415,"无此验证码！"),
    // 此活动无任何环节，
    STEP_EMPTY(416,"此活动无任何环节，请添加环节"),
    // 找不到此活动
    NOFIND_ACT_ERR(417,"找不到此活动"),
    NOFIND_STEP_ERR(417, "找不到此环节"),
    ITEM_EMPTY(418, "当前环节无任何评分项目"),
    DAFEN_ERR(419, "打分失败"),
    NO_FIND_USER_BY_ID(420, "没有根据ID查到此人"),
    ACT_NOT_START(421, "活动未开始"),
    ACT_END(422, "活动已结束"),
    DAFEN_ITEM_EMPTY(423, "打分项目为空"),
    DOUBLE_DAFWN_ERR(424, "您已给该选手打分，不能重复打分"),
    ACT_ADD_ERR(425, "活动创建失败"),
    TONG_ERR(426, "未知异常"),

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
