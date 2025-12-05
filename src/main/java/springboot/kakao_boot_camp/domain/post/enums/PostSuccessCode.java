package springboot.kakao_boot_camp.domain.post.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import springboot.kakao_boot_camp.global.api.SuccessCodeInterface;

@AllArgsConstructor
@Getter
public enum PostSuccessCode implements SuccessCodeInterface {

    // -- 1. base --
    POST_CREATE_SUCCESS(HttpStatus.CREATED, "게시글을 성공적으로 등록하였습니다."),
    POST_LIST_READ_SUCCESS(HttpStatus.OK, "게시글 목록을 성공적으로 조회하였습니다."),
    POST_DETAIL_READ_SUCCESS(HttpStatus.OK, "게시글 조회를 성공하였습니다."),
    POST_UPDATE_SUCCESS(HttpStatus.OK, "게시글을 성공적으로 수정하였습니다."),
    POST_DELETE_SUCCESS(HttpStatus.OK, "게시글을 성공적으로 삭제하였습니다."),



    // -- 2. like --
    POST_LIKE_SUCCESS(HttpStatus.OK, "게시글 좋아요를 성공적으로 실행하였습니다."),
    POST_DISLIKE_SUCCESS(HttpStatus.OK, "게시글 좋아요 취소를 성공적으로 실행하였습니다.");



    private final HttpStatus status;
    private final String message;
    }
