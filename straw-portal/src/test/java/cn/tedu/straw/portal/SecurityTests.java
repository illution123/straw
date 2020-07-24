package cn.tedu.straw.portal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
public class SecurityTests {

//    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void test(){
        String rawPassword = "1234";
        String encodePassword = passwordEncoder.encode(rawPassword);
        log.debug("raw password={},encode password={}",rawPassword,encodePassword);
    }
}
