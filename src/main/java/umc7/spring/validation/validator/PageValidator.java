package umc7.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc7.spring.apiPayload.code.status.ErrorStatus;
import umc7.spring.validation.annotation.CheckPage;

public class PageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    private boolean isValid;


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value <= 0) { // null이거나 0이하면 오류
            
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_LAW_NUMBER.toString()).addConstraintViolation();
        }
        else
            isValid = true;

        return isValid;
    }
}
