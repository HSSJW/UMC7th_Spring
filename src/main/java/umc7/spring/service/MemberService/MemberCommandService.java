package umc7.spring.service.MemberService;

import umc7.spring.domain.Member;
import umc7.spring.web.dto.MemberDto.MemberRequestDTO;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDTO.JoinDto request);
}
