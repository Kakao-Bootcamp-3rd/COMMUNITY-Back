package springboot.kakao_boot_camp.domain.auth.exception;

import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class NotAuthenticateUser extends BusinessException {
    public NotAuthenticateUser() {
        super(ErrorCode.UNAUTHORIZED);
    }

}
