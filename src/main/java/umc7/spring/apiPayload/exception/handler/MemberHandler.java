package umc7.spring.apiPayload.exception.handler;


public class MemberHandler extends RuntimeException {
    private final String errorCode;

    public MemberHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorCode = errorStatus.getCode();
    }

    public String getErrorCode() {
        return errorCode;
    }
}
