package umc7.spring.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc7.spring.apiPayload.exception.handler.ErrorStatus;
import umc7.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc7.spring.converter.MemberConverter;
import umc7.spring.converter.MemberPreferConverter;
import umc7.spring.domain.FoodCategory;
import umc7.spring.domain.Member;
import umc7.spring.domain.mapping.MemberPrefer;
import umc7.spring.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc7.spring.repository.MemberRepository.MemberRepository;
import umc7.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional //트랜잭션 설정
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
