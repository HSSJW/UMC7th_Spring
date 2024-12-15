package umc7.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc7.spring.validation.validator.PageValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageValidator.class) //PageValidator.java와 매칭된다.
@Target({ElementType.PARAMETER}) //파라미터를 검증한다
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "page의 값은 1이상이어야합니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
