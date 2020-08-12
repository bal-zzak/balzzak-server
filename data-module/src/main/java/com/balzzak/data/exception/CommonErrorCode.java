package com.balzzak.data.exception;

public enum CommonErrorCode implements ErrorCode {
    MISSING_INPUT_VALUE(400, "common-4000"),
    NO_HANDLER_FOUND(404, "common-4040"),
    METHOD_NOT_ALLOWED(405, "common-4050"),
    UNSUPPORTED_MEDIA_TYPE(415, "common-4150"),
    INTERNAL_SERVER_ERROR(500, "common-5000");

    private final int status;
    private final String code;

    CommonErrorCode(int status, String code) {
        this.status = status;
        this.code = code;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
