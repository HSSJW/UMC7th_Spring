package umc7.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc7.spring.service.StoreService.StoreQueryService;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"umc7.spring"}) // 패키지 스캔 설정
public class StudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext context) {
        return args -> {
            StoreQueryService storeService = context.getBean(StoreQueryService.class);

            try {
                Object resolver = context.getBean("pageArgumentResolver");
                System.out.println("PageArgumentResolver 빈 등록됨: " + resolver);
            } catch (Exception e) {
                System.out.println("PageArgumentResolver 빈 등록 실패: " + e.getMessage());
            }
        };

           


    }}
