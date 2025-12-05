package springboot.kakao_boot_camp.global.exception.handler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidLoginException;
import springboot.kakao_boot_camp.global.api.*;
import springboot.kakao_boot_camp.global.exception.BusinessException;
import springboot.kakao_boot_camp.global.exception.DuplicateResourceException;

@RestControllerAdvice
public class GlobalExceptionHandler {



    // -- BusinessException --
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e){
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.error(errorCode));
    }


    // -- Common --
    @ExceptionHandler(MethodArgumentNotValidException.class)                                                         // 400
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        return ResponseEntity
                .status(errorCode.getStatus())   // 400
                .body(ApiResponse.error(errorCode));
    }

    @ExceptionHandler(DuplicateResourceException.class)                                                              //409
    public ResponseEntity<ApiResponse<Void>> hanlderDuplicateResource(DuplicateResourceException e){
        ErrorCode errorCode = ErrorCode.DUPLICATE_EMAIL;
        return  ResponseEntity
                .status(errorCode.getStatus())                                    // 409
                .body(ApiResponse.error(errorCode));        // code : DUPLICATE_EMAIL, message : 이미 존재하는 이메일입니다
    }





}
