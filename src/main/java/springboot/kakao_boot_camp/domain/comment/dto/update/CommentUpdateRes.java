package springboot.kakao_boot_camp.domain.comment.dto.update;

import springboot.kakao_boot_camp.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentUpdateRes(
        Long commentId,
        Long parentId,
        String content,
        String nickname,
        String profileImage,
        LocalDateTime updatedAt
) {
    public static CommentUpdateRes of(Comment comment) {
        return new CommentUpdateRes(
                comment.getId(),
                comment.getParent() != null ? comment.getParent().getId() : null,
                comment.getContent(),
                comment.getUser().getNickName(),
                comment.getUser().getProfileImage(),
                comment.getUpdatedAt()
        );
    }
}
