package springboot.kakao_boot_camp.global.api;

import org.springframework.http.HttpStatus;

public interface SuccessCodeInterface {
    public HttpStatus getStatus();
    public String getMessage();

}
