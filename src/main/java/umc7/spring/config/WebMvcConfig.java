package umc7.spring.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import umc7.spring.validation.annotation.PageArgumentResolver;


import java.util.List;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final PageArgumentResolver pageArgumentResolver;

    public WebMvcConfig(PageArgumentResolver pageArgumentResolver) {
        this.pageArgumentResolver = pageArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        System.out.println("=== WebMvcConfig.addArgumentResolvers ===");
        resolvers.add(pageArgumentResolver); // Resolver 등록
        System.out.println("PageArgumentResolver has been registered.");
    }
}

