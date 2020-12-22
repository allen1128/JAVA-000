package io.kimmking.rpcfx.exception;

import lombok.Data;

@Data
public class RpcfxException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;
    private Throwable cause;

    public RpcfxException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public RpcfxException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public static enum ErrorCode {
        NO_AUTH,
        SERVICE_NOT_AVAIALBLE,
        TIMEOUT,
        INVALID_REQUEST;
    }
}
