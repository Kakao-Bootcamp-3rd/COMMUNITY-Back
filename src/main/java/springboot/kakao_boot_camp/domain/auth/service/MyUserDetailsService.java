package springboot.kakao_boot_camp.domain.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.kakao_boot_camp.domain.user.enums.UserRole;
import springboot.kakao_boot_camp.domain.user.exception.UserNotFoundException;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;
import springboot.kakao_boot_camp.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;


// 스프링 시큐리티에서 자동으로 호출

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<GrantedAuthority> auth = new ArrayList<>();


        // 유저 찾고
        springboot.kakao_boot_camp.domain.user.model.User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);


        // 역할 부여하고

        if (user.getRole() != null){

        }
        if (user.getRole().equals(UserRole.ROLE_USER)) {
            auth.add(new SimpleGrantedAuthority(UserRole.ROLE_USER.name()));
        }
        else if(user.getRole().equals(UserRole.ROLE_ADMIN)){
            auth.add(new SimpleGrantedAuthority((UserRole.ROLE_ADMIN.name())));
        }

        // 프로바이더가 토큰으로 받은 비밀번호와 비교할 DB에서 조회한 User객체 반환 -> 해당 유저 객체의 비밀번호를 프로바이더가 사용한다.
        CustomUserDetails customUserDetails = new CustomUserDetails(user, auth);  // email, password, authorities 등록
        customUserDetails.setId(user.getId());

        return customUserDetails;

    }


}

