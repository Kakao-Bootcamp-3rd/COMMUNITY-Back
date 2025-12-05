package springboot.kakao_boot_camp.domain.auth.dto.loginDtos;

public record LoginReq(
        String email,
        String passWord
) {
}
