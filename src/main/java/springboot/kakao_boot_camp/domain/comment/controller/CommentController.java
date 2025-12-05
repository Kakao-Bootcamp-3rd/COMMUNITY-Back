package springboot.kakao_boot_camp.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springboot.kakao_boot_camp.domain.comment.dto.create.CommentCreateReq;
import springboot.kakao_boot_camp.domain.comment.dto.create.CommentCreateRes;
import springboot.kakao_boot_camp.domain.comment.dto.delete.CommentDeleteRes;
import springboot.kakao_boot_camp.domain.comment.dto.read.CommentDetailRes;
import springboot.kakao_boot_camp.domain.comment.dto.read.CommentListRes;
import springboot.kakao_boot_camp.domain.comment.dto.update.CommentUpdateReq;
import springboot.kakao_boot_camp.domain.comment.dto.update.CommentUpdateRes;
import springboot.kakao_boot_camp.domain.comment.service.CommentService;
import springboot.kakao_boot_camp.global.api.ApiResponse;
import springboot.kakao_boot_camp.global.api.SuccessCode;
import springboot.kakao_boot_camp.security.CustomUserDetails;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // -- 1. 댓글 생성 --
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<CommentCreateRes>> create(@PathVariable Long postId,
                                                                @RequestBody CommentCreateReq commentCreateReq, @AuthenticationPrincipal CustomUserDetails currentUser) {
        CommentCreateRes res = commentService.createComment(currentUser.getId(), postId, commentCreateReq);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(SuccessCode.COMMENT_CREATE_SUCCESS, res));
    }

    // -- 2. 댓글 조회 --
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<CommentListRes>> getCommentList(@PathVariable Long postId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        CommentListRes res = commentService.getCommentList(postId, page, size);

        if (res.comments().isEmpty()) {
            return ResponseEntity.ok(ApiResponse.success(SuccessCode.COMMENT_LIST_EMPTY, res));
        }

        return ResponseEntity.ok(ApiResponse.success(SuccessCode.COMMENT_LIST_READ_SUCCESS, res));
    }
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentDetailRes>> getCommentDetail(@PathVariable Long commentId) {
        CommentDetailRes res = commentService.getCommentDetail(commentId);
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.COMMENT_READ_SUCCESS, res));
    }


    // -- 3. 댓글 수정 --
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentUpdateRes>> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateReq req
    ) {
        CommentUpdateRes res = commentService.updateComment(commentId, req);
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.COMMENT_UPDATE_SUCCESS, res));
    }


    // -- 4. 댓글 삭제 --
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentDeleteRes>> deleteComment(
            @PathVariable Long commentId
    ) {
        CommentDeleteRes res = commentService.deleteComment(commentId);
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.COMMENT_DELETE_SUCCESS, res));
    }


}





