package com.cliffdev.kv.contract;

import java.io.Serializable;

public class KvResponse<T> implements Serializable {

    public static final int SUCCESS = 0;
    public static final int ERROR   = 1;

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
