package com.bim.rssFeedReader.common;

import org.springframework.http.HttpStatus;

public class RSSFeedCustomException extends Exception {
    private int errorCode;
    private String errorMessage;

    public RSSFeedCustomException(Throwable throwable) {
        super(throwable);
    }

    public RSSFeedCustomException(String errorMessage, HttpStatus throwable) {
        super(errorMessage, throwable);
    }

    public RSSFeedCustomException(String errorMessage) {
        super(errorMessage);
    }

    public RSSFeedCustomException(String errorMessage, int errorCode) {
        super();
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
