package springboot.kakao_boot_camp.domain.post.dto.base;


import jakarta.validation.constraints.NotBlank;
import springboot.kakao_boot_camp.domain.post.entity.Post;
import springboot.kakao_boot_camp.global.dto.CursorInfo;

import java.time.LocalDateTime;
import java.util.List;

public class PostDtos {
    // -- 게시글 생성 ==
    public record PostCreateReq(
            @NotBlank(message = "제목이 비었습니다.")
            String title,

            @NotBlank(message = "내용이 비었습니다.")
            String content,

            String imageUrl
    ){}
    public record PostCreateRes(
            Long postId,
            String title,
            String content,
            String imageUrl,

            LocalDateTime createdAt
    ){
        public static PostCreateRes from(Post post){
            return new PostCreateRes(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getImageUrl(),
            post.getCreatedAt()
            );
        }
    }
    
    
    // -- 게시글 리스트 및 단건 조회  --
    public record PostListRes(
        List<PostSummary> posts,
        CursorInfo pageInfo
    ) {
        public static PostListRes of(List<PostSummary> posts, CursorInfo pageInfo) {
            return new PostListRes(posts, pageInfo);
        }

        // 📝 게시글 요약 정보
        public record PostSummary(
                Long postId,
                String title,
                String nickname,
                String profileImageUrl,

                int likeCount,
                int commentCount,
                int viewCount,

                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) {
            public static PostSummary of(
                    Long postId,
                    String title,
                    String nickname,
                    String profileImageUrl,
                    int likeCount,
                    int commentCount,
                    int viewCount,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt
            ) {
                return new PostSummary(
                        postId,
                        title,
                        nickname,
                        profileImageUrl,
                        likeCount,
                        commentCount,
                        viewCount,
                        createdAt,
                        updatedAt
                );
            }
        }



    }
    public record PostDetailRes(
            Long postId,
            String title,
            String content,
            String imageUrl,

            int likeCount,
            int viewCount,
            int commentCount,

            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
        public static PostDetailRes from(Post post){
            return new PostDetailRes(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getImageUrl(),
            post.getLikeCount(),
            post.getViewCount(),
            post.getCommentCount(),
            post.getCreatedAt(),
            post.getUpdatedAt()
            );
        }

    }


    // -- 게시글 수정  --
    public record PostUpdateReq(
            String title,
            String content,
            String imageUrl
    ){}
    public record PostUpdateRes(            // 수정하고 바로 화면에 반영하기 위해 전체 정보 내려줌
            Long postId,
            String title,
            String content,
            String imageUrl,

            int likeCount,
            int viewCount,
            int commentCount,

            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
        public static PostUpdateRes from(Post post){
            return new PostUpdateRes(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getImageUrl(),
            post.getLikeCount(),
            post.getViewCount(),
            post.getCommentCount(),
            post.getCreatedAt(),
            post.getUpdatedAt()
            );
        }
    }


    // -- 게시글 삭제  --
    public record PostDeleteReq(){}
    public record PostDeleteRes(
            Long postId,
//            boolean deleted,        // 추후 soft 삭제 시 사용
            LocalDateTime deletedAt
    ){
        public static PostDeleteRes from(Long id, /*boolean deleted,*/ LocalDateTime deletedAt){
            return new PostDeleteRes(id, /*deleted,*/ deletedAt);
        }
    }
    
}