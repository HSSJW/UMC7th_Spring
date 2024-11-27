package umc7.spring.apiPayload.exception.handler;
public class FoodCategoryHandler extends RuntimeException {
    private final String errorCode;

    public FoodCategoryHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorCode = errorStatus.getCode();
    }

    public String getErrorCode() {
        return errorCode;
    }
}

