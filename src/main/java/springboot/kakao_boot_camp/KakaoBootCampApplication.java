package springboot.kakao_boot_camp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(
    exclude = {ValidationAutoConfiguration.class,
                SecurityAutoConfiguration.class } // ← Security 자동설정 제외

)

@EnableJpaAuditing
public class KakaoBootCampApplication {

    public static void main(String[] args) {
       SpringApplication.run(KakaoBootCampApplication.class, args);
    }

}