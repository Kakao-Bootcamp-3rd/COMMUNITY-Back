package springboot.kakao_boot_camp.domain.auth.dto.signDtos;

import jakarta.validation.constraints.Email;
import lombok.NonNull;

// 1. Sign Up
public record SignReq(
        @Email
        @NonNull
        String email,

        @NonNull
        String passWord,

        String nickName

) {
}
