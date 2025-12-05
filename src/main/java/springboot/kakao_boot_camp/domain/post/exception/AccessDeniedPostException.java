package springboot.kakao_boot_camp.domain.post.exception;

import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class AccessDeniedPostException extends BusinessException {
    public  AccessDeniedPostException(){
        super(ErrorCode.POST_DENIED);
    }
}
