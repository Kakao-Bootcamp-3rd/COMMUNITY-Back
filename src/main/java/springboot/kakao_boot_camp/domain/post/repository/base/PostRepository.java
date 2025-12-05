package springboot.kakao_boot_camp.domain.post.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.kakao_boot_camp.domain.post.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 첫 페이지 (cursor = 0)
    @Query("SELECT p FROM Post p ORDER BY p.id DESC LIMIT :limit")
    List<Post> findTopNByOrderByIdDesc(@Param("limit") int limit);

    // 다음 페이지 (cursor < id)
    @Query("SELECT p FROM Post p WHERE p.id < :cursor ORDER BY p.id DESC LIMIT :limit")
    List<Post> findTopNByIdLessThanOrderByIdDesc(@Param("cursor") Long cursor, @Param("limit") int limit);

}
