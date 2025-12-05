package springboot.kakao_boot_camp.domain.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import springboot.kakao_boot_camp.domain.user.model.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String content;

    String imageUrl;

    @Column(nullable = false)
    int likeCount = 0;      // default 0

    @Column(nullable = false)
    int viewCount= 0;

    @Column(nullable = false)
    int commentCount = 0;

    @CreatedDate
    LocalDateTime createdAt;

    LocalDateTime updatedAt;




}
