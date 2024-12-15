package umc7.spring.validation.annotation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    private final Validator validator;

    // Hibernate Validator 인스턴스 생성
    public PageArgumentResolver() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(CheckPage.class);
        boolean isInteger = parameter.getParameterType().equals(Integer.class);

        System.out.println("=== PageArgumentResolver.supportsParameter ===");
        System.out.println("Parameter: " + parameter.getParameterName());
        System.out.println("Has @CheckPage annotation: " + hasAnnotation);
        System.out.println("Is Integer type: " + isInteger);

        return hasAnnotation && isInteger;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String pageParam = webRequest.getParameter("page");
        System.out.println("=== PageArgumentResolver.resolveArgument ===");
        System.out.println("Original page parameter: " + pageParam);

        // page 값을 기본값 1로 설정
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        // Hibernate Validator를 사용해 유효성 검증
        var violations = validator.validateValue(
                CheckPage.class, // 검증 대상 클래스
                "value",         // 대상 필드 이름 (dummy)
                page             // 검증할 값
        );

        // 검증 실패 시 예외 발생
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("page의 값은 1 이상이어야 합니다.");
        }

        // 검증된 값을 기반으로 1 감소
        int adjustedPage = Math.max(page - 1, 0);
        System.out.println("Final adjusted page value: " + adjustedPage);
        return adjustedPage;
    }
}
