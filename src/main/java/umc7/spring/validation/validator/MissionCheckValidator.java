package umc7.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Component;
import umc7.spring.apiPayload.code.status.ErrorStatus;
import umc7.spring.domain.Mission;
import umc7.spring.repository.MissionRepository.MissionRepository;
import umc7.spring.service.MissionService.MissionQueryService;
import umc7.spring.validation.annotation.CheckMission;


@Component
@RequiredArgsConstructor // final이나 @NonNull이 붙은 필드만을 대상으로 생성자를 자동 생성
public class MissionCheckValidator implements ConstraintValidator<CheckMission, Long> {

    private final MissionQueryService missionQueryService;


    @Override
    public void initialize(CheckMission constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext constraintValidatorContext) {
    Long memberId= 1L;
    if(missionId == null){ //미션id를 전달받지 못했을 때
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString()).addConstraintViolation();

    }
    boolean isValid = missionQueryService.isChallengedMission(memberId, missionId); //이미 진행 중인 미션인지 판별하는 함수
    
    if(isValid){ //이미 진행중인 미션인 경우
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_IS_CHALLENGING.toString()).addConstraintViolation();

    }

        return isValid; //false인 경우 >> 도전 가능
    }



}
