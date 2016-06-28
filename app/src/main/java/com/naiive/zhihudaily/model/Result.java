package com.naiive.zhihudaily.model;

/**
 * Created by wangzhe on 16/6/28.
 */
public class Result<T> {

    private int code = -1;

    private T result;

    public Result() {
    }

    public Result(int code, T result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public interface Code {
        int DATA_WITH_UPDATED = 0;
        int DATA_NOT_UPDATED = 1;
        int DATA_NULL = 2;
    }

}
