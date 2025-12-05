package springboot.kakao_boot_camp.domain.auth.util;

import org.springframework.stereotype.Component;
import org.mindrot.jbcrypt.BCrypt;

@Component
public class CustomPasswordEncoder {
    public String encode(String raw) {
        return BCrypt.hashpw(raw, BCrypt.gensalt());
    }

    public boolean match(String raw, String hashedPassword) {
        return BCrypt.checkpw(raw, hashedPassword);
    }
}
