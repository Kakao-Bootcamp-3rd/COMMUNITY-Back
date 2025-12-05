package springboot.kakao_boot_camp.domain.post.repository.like;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.kakao_boot_camp.domain.post.entity.PostLike;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    Optional<PostLike> findByUserIdAndPostId(Long userID, Long postId);
}
