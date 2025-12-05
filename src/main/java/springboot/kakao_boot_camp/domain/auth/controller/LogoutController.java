package springboot.kakao_boot_camp.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.kakao_boot_camp.domain.auth.service.LogoutService;
import springboot.kakao_boot_camp.global.api.ApiResponse;
import springboot.kakao_boot_camp.global.api.SuccessCode;
import springboot.kakao_boot_camp.security.CustomUserDetails;

@RestController
@RequestMapping("/api/v1/auth/logout")
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request, HttpServletResponse response,
                                                    @AuthenticationPrincipal CustomUserDetails currentUser) {

        logoutService.logout(currentUser, request, response);

        return ResponseEntity.ok(ApiResponse.success(SuccessCode.LOGOUT_SUCCESS, null));
    }
}
