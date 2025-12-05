package springboot.kakao_boot_camp.domain.auth.util.Manager.login.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import springboot.kakao_boot_camp.domain.auth.util.JwtUtil;


@Component
@RequiredArgsConstructor
public class JwtAuthManager {
    private final JwtUtil jwtUtil;

    public TokenPair createTokens(Authentication token){
        String accessToken = jwtUtil.createAccessToken(token);
        String refreshToken = jwtUtil.createRefreshToken(token);

        return TokenPair.from(accessToken, refreshToken);
    }

    public record TokenPair(String accessToken, String refreshToken) {
        public static TokenPair from(String accessToken, String refreshToken){
            return new TokenPair(accessToken, refreshToken);
        }
    }
}