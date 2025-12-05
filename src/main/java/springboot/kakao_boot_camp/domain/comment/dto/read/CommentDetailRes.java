package springboot.kakao_boot_camp.domain.comment.dto.read;

import springboot.kakao_boot_camp.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentDetailRes(
        Long commentId,
        Long parentId,
        Long postId,
        Long userId,
        String nickname,
        String profileImageUrl,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CommentDetailRes of(Comment comment) {
        return new CommentDetailRes(
                comment.getId(),
                comment.getParent() != null ? comment.getParent().getId() : null,
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getUser().getNickName(),
                comment.getUser().getProfileImage(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
