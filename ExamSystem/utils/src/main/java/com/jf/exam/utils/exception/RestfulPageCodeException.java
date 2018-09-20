package com.jf.exam.utils.exception;

public class RestfulPageCodeException extends RuntimeException {
    public String url;

    public RestfulPageCodeException(String message, String url) {
        super(message);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
