package springboot.kakao_boot_camp.domain.auth.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidTokenTypeException;
import springboot.kakao_boot_camp.domain.auth.exception.JwtTokenExpiredException;
import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.security.CustomUserDetails;


@Component
public class JwtUtil {

    private final SecretKey accessKey;
    private final SecretKey refreshKey;
    private final long accessTtl;
    private final long refreshTtl;

    // 생성자 주입
    public JwtUtil(
            @Value("${jwt.access.secret}") String accessSecret,
            @Value("${jwt.refresh.secret}") String refreshSecret,
            @Value("${jwt.access.expiration}") long accessTtl,
            @Value("${jwt.refresh.expiration}") long refreshTtl
    ) {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret));
        this.accessTtl = accessTtl;
        this.refreshTtl = refreshTtl;
    }

    // Access Token 생성
    public String createAccessToken(Authentication auth) {
        return createToken(auth, accessKey, accessTtl);
    }

    // Refresh Token 생성
    public String createRefreshToken(Authentication auth) {
        return createToken(auth, refreshKey, refreshTtl);
    }

    // 공통 토큰 생성 로직
    private String createToken(Authentication auth, SecretKey key, long ttl) {
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

        String authorities = auth.getAuthorities().stream()                 //getAuthorities -> List<auth객체> return
                .map(a -> a.getAuthority())   // getAuthority() -> String return
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())  // <-- jti 추가
                .claim("userId", user.getId())
                .claim("email", user.getUsername())
                .claim("role", authorities)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ttl))
                .signWith(key)
                .compact();
    }

    // Access Token 파싱
    public Claims extractAccessToken(String token) {
        return extractToken(token, accessKey);
    }

    // Refresh Token 파싱
    public Claims extractRefreshToken(String token) {
        return extractToken(token, refreshKey);
    }

    // 공통 파싱 로직
    public static Claims extractToken(String token, SecretKey key) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            // 토큰 만료 예외를 커스텀 예외로 던짐
            throw new JwtTokenExpiredException();
        } catch (JwtException | IllegalArgumentException e) {
            // 잘못된 토큰 예외를 커스텀 예외로
            System.out.println("JWT Parsing Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();

            throw new InvalidTokenTypeException();
        }

    }

    // 토큰 유효성 검증 (Access / Refresh 구분)
    public boolean validateToken(String token, boolean isAccessToken) {
        try {
            if (isAccessToken) {
                extractAccessToken(token);
            } else {
                extractRefreshToken(token);
            }
            return true; // 예외가 안 나면 유효
        } catch (JwtTokenExpiredException e) {
            System.out.println("토큰 만료: " + e.getMessage());
            return false;
        } catch (InvalidTokenTypeException | JwtException e) {
            System.out.println("유효하지 않은 토큰: " + e.getMessage());
            return false;
        }
    }
}



