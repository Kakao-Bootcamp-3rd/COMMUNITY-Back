package springboot.kakao_boot_camp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springboot.kakao_boot_camp.domain.post.service.base.PostService;
import springboot.kakao_boot_camp.domain.post.dto.base.PostDtos.*;
import springboot.kakao_boot_camp.domain.post.entity.Post;
import springboot.kakao_boot_camp.domain.post.repository.base.PostRepository;
import springboot.kakao_boot_camp.domain.user.model.User;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void createPostTest() {


        // given
        User user = User.builder()
                .Id(1L)
                .build();
        given(userRepository.findById(1L))
                .willReturn(Optional.of(user));
        given(postRepository.save(any(Post.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        // when
        PostCreateReq req = new PostCreateReq("제목", "내용", "이미지");
        PostCreateRes res = postService.createPost(1L,req);


        // then
        assertThat(res.title()).isEqualTo("제목");
    }
}
