package springboot.kakao_boot_camp.domain.user.exception;

import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(){
        super(ErrorCode.User_NOT_FOUND);
    }

}
