package com.gamazing.nettychat.common.enums;

public enum ResultCode {

    SUCCESS(0, "success"),
    FAIL(1, "fail"),
    BAD_REQUEST(2, "bad request"),
    USERNAME_TYPE_EMPTY(1000, "username is empty"),
    PWD_TYPE_EMPTY(1001, "password is empty"),
    USERNAME_OR_PASSWORD_ERROR(1002, "username or password error"),
    USER_IMAGE_EMPTY(1003, "Please choose photo"),
    IMAGE_FORMAT_ERROR(1004, "Photo format error"),
    ;

    private int status;
    private String message;

    ResultCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ResultCode getCode(int status) {
        for (ResultCode c : ResultCode.values()) {
            if (c.getStatus() == status) {
                return c;
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
