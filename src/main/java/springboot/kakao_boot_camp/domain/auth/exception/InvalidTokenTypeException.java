package springboot.kakao_boot_camp.domain.auth.exception;


import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class InvalidTokenTypeException extends BusinessException {
    public InvalidTokenTypeException(){
        super(ErrorCode.INVALID_TOKEN_TYPE);
    }
}
