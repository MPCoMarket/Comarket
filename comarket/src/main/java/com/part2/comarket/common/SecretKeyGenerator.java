package com.part2.comarket.common;

import java.security.SecureRandom;
import java.util.Base64;

class SecretKeyGenerator {
    public static String generateSecretKey() {
        // SecureRandom JAVA에서 제공하는 암호학적으로 안전한 난수 생성기
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32]; // 256bits
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
