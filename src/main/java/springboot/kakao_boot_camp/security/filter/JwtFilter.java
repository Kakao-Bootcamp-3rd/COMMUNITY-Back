package springboot.kakao_boot_camp.security.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidTokenTypeException;
import springboot.kakao_boot_camp.domain.auth.exception.JwtTokenExpiredException;
import springboot.kakao_boot_camp.domain.auth.util.JwtUtil;
import springboot.kakao_boot_camp.domain.auth.util.Manager.login.jwt.TokenBlacklistManager;
import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.security.CustomUserDetails;
import springboot.kakao_boot_camp.security.handler.CustomAuthenticationEntryPoint;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final TokenBlacklistManager tokenBlacklistManager;


    private String USER_ID_KEY = "userId";
    private String EMAIL_KEY = "email";
    private String ROLE_KEY = "role";

    private static final String BEARER = "Bearer";

    @Value("${jwt.access.secret}")
    String accessSecret;


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. 이미 인증된 상태면 패스
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }



        // 2. 토큰 확인
        String token = resolveAccessToken(request);         // 토큰있는지 문자열 체크
         if (token == null) { // 토큰 없으면 그냥 통과
            filterChain.doFilter(request, response);        // 넘어가요
            return;
        }



        try {
            // 3. 토큰 까서 claims 획득
            Claims claims = jwtUtil.extractAccessToken(token);    // 토큰 까기 (토큰 진위여부 검증 해당 메서드에서 진행)



            // 4. 블랙리스트 체크
            String jti = claims.getId();
            if (jti != null && tokenBlacklistManager.isBlacklisted(jti)) {
                request.setAttribute("exception", ErrorCode.TOKEN_LOGOUTED);
                customAuthenticationEntryPoint.commence(request, response, new AuthenticationException("Token logged out") {
                });
                return;
            }



            //5. 블랙리스트 아닐 시, claim 페이로드 값 반환
            Long userId = claims.get("userId", Number.class).longValue();
            String email = claims.get("email").toString();
            var authorities = Arrays.stream(
                    claims.get("role").toString().split(",")
            ).map(SimpleGrantedAuthority::new).toList();



            // 6. 인증 정보 저장 = SecurityContextHolder에 등록
            CustomUserDetails customUserDetails = CustomUserDetails.from(userId, email, authorities);
            var auth = new UsernamePasswordAuthenticationToken(
                    customUserDetails, null, customUserDetails.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));     // 추가 정보 저장 (어디서 접속했는지)
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (JwtTokenExpiredException e) {
            request.setAttribute("exception", ErrorCode.TOKEN_EXPIRED);
            customAuthenticationEntryPoint.commence(request, response, new AuthenticationException("Token expired") {
            });
            return;
        } catch (InvalidTokenTypeException e) {
            request.setAttribute("exception", ErrorCode.INVALID_TOKEN_TYPE);
            customAuthenticationEntryPoint.commence(request, response, new AuthenticationException("Invalid token") {
            });
            return;
        }


        // 7. 다음 필터로 진행
        filterChain.doFilter(request, response);
    }


    private String resolveAccessToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(BEARER)) {
            String token = header.substring(BEARER.length()).trim();
            if(!token.equals("null"))
                return token;
        }
        return null;
    }
}



