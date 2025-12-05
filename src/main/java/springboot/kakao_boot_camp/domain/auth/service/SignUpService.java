package springboot.kakao_boot_camp.domain.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.auth.util.Manager.signup.SignUpManager;
import springboot.kakao_boot_camp.domain.auth.dto.signDtos.SignReq;
import springboot.kakao_boot_camp.domain.auth.dto.signDtos.SignRes;
import springboot.kakao_boot_camp.domain.auth.exception.DuplicateEmailException;
import springboot.kakao_boot_camp.domain.user.enums.UserRole;
import springboot.kakao_boot_camp.domain.user.model.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;
//import springboot.kakao_boot_camp.global.util.JwtUtil;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SignUpService {      //Dto로 컨트롤러에서 받음

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final SignUpManager signUpManager;

    public SignRes signUp(SignReq req) throws RuntimeException {


        // 1. 중복 확인
        if (userRepo.existsByEmail(req.email())) {
            throw new DuplicateEmailException();
        }


        UserRole userRole = UserRole.ROLE_USER;

        if (signUpManager.isAdmin( req.email())){
            userRole=UserRole.ROLE_ADMIN;
        }

        User user = User.builder()
                .email(req.email())
                .passWord(passwordEncoder.encode(req.passWord()))
                .nickName(req.nickName())
                .profileImage(null)
                .role(userRole)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        // 3. DB에 저장
        User savedUSer = userRepo.save(user);

        // 4. Sign Response DTO 반환
        return new SignRes(savedUSer.getId());

    }



}

