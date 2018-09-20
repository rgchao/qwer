package com.jf.exam.utils.exception;
/**
 * 页码异常
 */
public class PageCodeException extends RuntimeException{
    int maxPageCode;
    public PageCodeException(String message,int maxPageCode){
        super(message);
        this.maxPageCode=maxPageCode;
    }

    public int getMaxPageCode() {
        return maxPageCode;
    }

    public void setMaxPageCode(int maxPageCode) {
        this.maxPageCode = maxPageCode;
    }
}
