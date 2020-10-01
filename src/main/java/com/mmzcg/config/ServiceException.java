package com.mmzcg.config;

public class ServiceException extends RuntimeException{

    private Integer ret;

    private Object data;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, int ret) {
        super(message);
        this.ret = ret;
    }

    public ServiceException(int ret) {
        super("");
        this.ret = ret;
    }

    public ServiceException(int ret, Object data) {
        super("");
        this.ret = ret;
        this.data = data;
    }

    public Integer getRet() {
        return ret;
    }

    public Object getData() {
        return data;
    }
}
