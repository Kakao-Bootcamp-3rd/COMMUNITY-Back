package springboot.kakao_boot_camp.domain.comment.dto.create;

import springboot.kakao_boot_camp.domain.comment.entity.Comment;

public record CommentCreateRes(
        Long commentId
) {
    public static CommentCreateRes from(Comment comment) {
        return new CommentCreateRes(
                comment.getId()
        );
    }
}
