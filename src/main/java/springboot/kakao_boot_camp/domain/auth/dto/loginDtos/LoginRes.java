package springboot.kakao_boot_camp.domain.auth.dto.loginDtos;

import springboot.kakao_boot_camp.security.CustomUserDetails;

public record LoginRes(
        Long userId,
        String accessToken,
        String refreshToken
) {
    public static LoginRes from(CustomUserDetails user, String accessToken, String refreshToken) {
        return new LoginRes(
                user.getId(),
                accessToken,
                refreshToken
        );
    }

    public static LoginRes fromWithoutRefreshToken(Long userId, String accessToken) {
        return new LoginRes(
                userId,
                accessToken,
                null
        );
    }
}
