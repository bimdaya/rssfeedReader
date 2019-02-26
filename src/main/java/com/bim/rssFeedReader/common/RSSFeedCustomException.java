package com.bim.rssFeedReader.common;

/**
 * Exception handling class for RSFeedApplication
 *
 * @author Bimali Dayananda
 */
public class RSSFeedCustomException extends Exception {
    private int errorCode;
    private String errorMessage;

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

    private void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
