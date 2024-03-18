package com.part2.comarket.company.command.value;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import java.util.UUID;

@NoArgsConstructor
@Getter
public class SecretKey {
    @Column(name = "secret_key")
    @Unique
    private String value;

    private SecretKey(String value) {
        this.value = value;
    }

    public static SecretKey generateKey() {
        String key = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
        return new SecretKey(key);
    }
}
