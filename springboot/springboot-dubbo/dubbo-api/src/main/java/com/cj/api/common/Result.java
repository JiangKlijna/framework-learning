package com.cj.api.common;

public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Resutl{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static Result success() {
        return new Result<>(0, "", null);
    }

    public static Result failure() {
        return new Result<>(1, "", null);
    }

    public static <T> Result success(T data) {
        return new Result<>(0, "", data);
    }

    public static <T> Result failure(T data) {
        return new Result<>(1, "", data);
    }

    public static <T> Result success(String message, T data) {
        return new Result<>(0, message, data);
    }

    public static <T> Result failure(String message, T data) {
        return new Result<>(1, message, data);
    }
}
