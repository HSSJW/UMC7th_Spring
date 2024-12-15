package umc7.spring.apiPayload.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import umc7.spring.apiPayload.code.status.ErrorStatus;

public class PageHandler extends RuntimeException {
    private final String errorCode;


    public PageHandler(ErrorStatus errorCode) {
        this.errorCode = errorCode.getCode();
    }

    public String getErrorCode() {return errorCode;}

}
