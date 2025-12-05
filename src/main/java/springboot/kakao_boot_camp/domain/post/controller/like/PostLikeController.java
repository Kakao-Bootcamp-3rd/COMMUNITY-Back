package springboot.kakao_boot_camp.domain.post.controller.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.kakao_boot_camp.domain.post.dto.base.PostDtos;
import springboot.kakao_boot_camp.domain.post.enums.PostSuccessCode;
import springboot.kakao_boot_camp.domain.post.service.like.PostLikeService;
import springboot.kakao_boot_camp.global.api.ApiResponse;
import springboot.kakao_boot_camp.global.api.SuccessCode;
import springboot.kakao_boot_camp.security.CustomUserDetails;

@RestController
@RequestMapping("/api/v1/like/posts")
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    // -- R --
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> likeOrUnlikePost(@AuthenticationPrincipal CustomUserDetails currentUser
    ,@ PathVariable Long postId) {

        boolean liked = postLikeService.postLikeOrUnlike(currentUser.getId(), postId);

        if (liked) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.success(PostSuccessCode.POST_LIKE_SUCCESS, null));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.success(PostSuccessCode.POST_DISLIKE_SUCCESS, null));
        }
    }



}
