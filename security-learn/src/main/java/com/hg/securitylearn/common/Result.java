package com.hg.securitylearn.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;

/**
 * @author hougen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    private Result() {
    }

    public static <T> Result<T> success(Integer code, String msg) {
        Result<T> re = new Result<>();
        re.code = code;
        re.msg = msg;
        return re;
    }

    public static <T> Result<T> success(Integer code, T data) {
        Result<T> re = new Result<>();
        re.code = code;
        re.data = data;
        return re;
    }

    public static <T> Result<T> auto(boolean result) {
        return result ? Result.success() : Result.fail();
    }

    public static <T> Result<T> success(ResultEnums resultEnums) {
        Result<T> re = new Result<>();
        re.code = resultEnums.code;
        re.msg = resultEnums.msg;
        return re;
    }

    public static <T> Result<T> success() {
        Result<T> re = new Result<>();
        re.code = ResultEnums.SUCCESS.code;
        re.msg = ResultEnums.SUCCESS.msg;
        return re;
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> re = new Result<>();
        re.code = code;
        re.msg = msg;
        return re;
    }

    public static <T> Result<T> fail() {
        Result<T> re = new Result<>();
        re.code = ResultEnums.FAIL.code;
        re.msg = ResultEnums.FAIL.msg;
        return re;
    }

    public static <T> Result<T> fail(ResultEnums resultEnums) {
        Result<T> re = new Result<>();
        re.code = resultEnums.code;
        re.msg = resultEnums.msg;
        return re;
    }

    @AllArgsConstructor
    public enum ResultEnums {
        SUCCESS(200, "success"),
        FAIL(505, "error"),
        ;

        private final Integer code;
        private final String msg;
    }
}
