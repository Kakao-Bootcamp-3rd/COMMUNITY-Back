package springboot.kakao_boot_camp.domain.post.service.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.kakao_boot_camp.domain.post.entity.Post;
import springboot.kakao_boot_camp.domain.post.entity.PostLike;
import springboot.kakao_boot_camp.domain.post.exception.PostNotFoundException;
import springboot.kakao_boot_camp.domain.post.repository.base.PostRepository;
import springboot.kakao_boot_camp.domain.post.repository.like.PostLikeRepository;
import springboot.kakao_boot_camp.domain.user.exception.UserNotFoundException;
import springboot.kakao_boot_camp.domain.user.model.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;


    // 1. 포스트 좋아요
    @Transactional
    public boolean postLikeOrUnlike(Long userId, Long postId) {

        // 1. 포스트 아이디 얻기
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        // 2. 유저 아이디 얻기
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);


        // 3. 해당 유저가 이미 좋아요를 눌렀는지 체크
        // 3.1 유저 좋아요 정보 가져오기
        Optional<PostLike> opt = postLikeRepository.findByUserIdAndPostId(userId, postId);

        // 3.2 좋아요 정보가 없으면 생성 후 해당 게시글 좋아요 카운트 +1
        if (opt.isEmpty()) {
            PostLike postLike = PostLike.builder()
                    .user(user)
                    .post(post)
                    .build();

            postLikeRepository.save(postLike);

            post.setLikeCount(post.getLikeCount() + 1);


            return true;
        }

        // 3.2 좋아요 정보가 있으면 삭제 후 해당 게시글 좋아요 카운트 -1
        else {
            postLikeRepository.delete(opt.get());

            if (post.getLikeCount() >= 0) {
                post.setLikeCount(post.getLikeCount() - 1);
            }

            return false;
        }


    }


}


