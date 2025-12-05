package springboot.kakao_boot_camp.global.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


// Todo 1. 추후 성공과 실패 코드가 아닌 도메인별로 나눌 필요
@AllArgsConstructor
@Getter
public enum ErrorCode {

    // -- Auth
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청 인자입니다."),                          // 400 회원 가입
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 이메일 입니다."),                           // 409 회원 가입
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 잘 못 되었습니다."),  // 401 로그인
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),


    // -- JWT --
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "로그인 토큰이 만료되었습니다. 다시 로그인해주세요."),
    INVALID_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰 형식입니다. 다시 로그인해주세요."),
    TOKEN_LOGOUTED(HttpStatus.UNAUTHORIZED, "이미 로그아웃된 토큰입니다."),

    // -- SESSION --
    SESSION_EXPIRED(HttpStatus.UNAUTHORIZED, "세션이 만료되었습니다. 다시 로그인해주세요."),

    // -- User --
    User_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),                    //404
    NOT_HAVE_ADMIN_ROLE(HttpStatus.BAD_REQUEST, "관리자 권한이 없는 유저입니다."),
    ALREADY_DELETE_USR(HttpStatus.CONFLICT, "이미 삭제한 유저입니다."),                      //409, 이미 삭제한 유저기때문에 conflict 충돌이라는 의미
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "변경할 비밀번호와 확인 비밀번호가 일치하지 않습니다."),                      //409, 이미 삭제한 유저기때문에 conflict 충돌이라는 의미
    WRONG_CURRENT_PASSWORD(HttpStatus.UNAUTHORIZED, "현재 비밀번호가 올바르지 않습니다."),                      //409, 이미 삭제한 유저기때문에 conflict 충돌이라는 의미

    // -- Post --
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시글을 찾을 수 없습니다."),                    //404
    POST_DENIED(HttpStatus.FORBIDDEN, "본인의 게시글만 수정/삭제할 수 있습니다."),                    //404


    // --  Comment --
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 댓글 찾을 수 없습니다.");                     //404


    private final HttpStatus status;
    private final String message;
}
