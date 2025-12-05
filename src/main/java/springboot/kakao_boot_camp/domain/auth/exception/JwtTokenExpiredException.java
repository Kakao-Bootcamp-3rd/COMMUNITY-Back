package springboot.kakao_boot_camp.domain.auth.exception;

import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class JwtTokenExpiredException extends BusinessException {
     public JwtTokenExpiredException(){
         super(ErrorCode.TOKEN_EXPIRED);
     }
}
