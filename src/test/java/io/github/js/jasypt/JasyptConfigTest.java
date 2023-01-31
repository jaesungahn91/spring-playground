package io.github.js.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class JasyptConfigTest {

    @Autowired
    @Qualifier("encryptorBean")
    private StringEncryptor stringEncryptor;

    @Test
    void 패스워드_생성() {
        String encrypt = stringEncryptor.encrypt("password");
        log.info(encrypt);
        String decrypt = stringEncryptor.decrypt(encrypt);
        log.info(decrypt);
    }
}