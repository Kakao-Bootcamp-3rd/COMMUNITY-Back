package springboot.kakao_boot_camp.global.manager;

import jakarta.servlet.http.HttpServletRequest;

public interface CustomAuthManager {
    public void create(HttpServletRequest httpServletRequest, Object object);
}
