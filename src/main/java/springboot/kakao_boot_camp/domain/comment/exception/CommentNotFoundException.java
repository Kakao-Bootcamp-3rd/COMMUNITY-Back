package springboot.kakao_boot_camp.domain.comment.exception;

import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class CommentNotFoundException extends BusinessException {
    public CommentNotFoundException(){
        super(ErrorCode.COMMENT_NOT_FOUND);
    }

}
