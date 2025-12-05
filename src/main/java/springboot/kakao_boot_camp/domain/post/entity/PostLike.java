package springboot.kakao_boot_camp.domain.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import springboot.kakao_boot_camp.domain.user.model.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Table(name = "post_like")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    public PostLike() {

    }


    // 좋아요는 수정 시각이 필요가 없음

}
