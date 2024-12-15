package umc7.spring.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Review", description = "리뷰관련 Api")
@Validated
@RestController
@RequestMapping("reviews")
public class ReviewRestControoler {
}
