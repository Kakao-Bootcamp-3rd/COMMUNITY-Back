package springboot.kakao_boot_camp.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.auth.util.Manager.login.jwt.JwtAuthManager;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.LoginReq;
import springboot.kakao_boot_camp.domain.auth.dto.loginDtos.LoginRes;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidLoginException;
import springboot.kakao_boot_camp.security.CustomUserDetails;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final JwtAuthManager jwtAuthManager;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    // 1. jwt 기반 로그인
    public LoginRes login(LoginReq req, HttpServletRequest servletReq) throws RuntimeException {

        // 1. 토큰 생성
        var token = new UsernamePasswordAuthenticationToken(req.email(), req.passWord());

        // 2. 로그인 검증
        try {
            var auth = authenticationManagerBuilder.getObject().authenticate(token);


            JwtAuthManager.TokenPair tokenPair = jwtAuthManager.createTokens(auth);


            return LoginRes.from((CustomUserDetails) auth.getPrincipal(), tokenPair.accessToken(), tokenPair.refreshToken());

        } catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new InvalidLoginException();
        } catch (AuthenticationException e) {
            throw new InvalidLoginException();
        }
    }

}

