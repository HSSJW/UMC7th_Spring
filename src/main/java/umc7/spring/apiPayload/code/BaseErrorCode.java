package umc7.spring.apiPayload.code;

public interface BaseErrorCode {

    ErrorReason getReason();

    ErrorReason getReasonHttpStatus();
}
