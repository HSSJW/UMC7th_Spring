package umc7.spring.converter;

import umc7.spring.web.dto.TempDto.TempResponseDto;

public class TempConverter {

    public static TempResponseDto.TempTestDto toTempTestDto(){
        return TempResponseDto.TempTestDto.builder()
                .testString("This is Test!")
                .build();
    }

    public static TempResponseDto.TempExceptionDto toTempExceptionDto(Integer flag){
        return TempResponseDto.TempExceptionDto.builder()
                .flag(flag)
                .build();
    }
}
