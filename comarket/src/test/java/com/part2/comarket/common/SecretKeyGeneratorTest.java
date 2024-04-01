package com.part2.comarket.common;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecretKeyGeneratorTest {

    @Test
    void makeSecretKey() {
        String key = SecretKeyGenerator.generateSecretKey();

//        System.out.println("key : " + key);

        // 길이 검증 테스트
        byte[] decodedKey = Base64.getDecoder().decode(key);
        assertEquals(32, decodedKey.length);
    }

}
