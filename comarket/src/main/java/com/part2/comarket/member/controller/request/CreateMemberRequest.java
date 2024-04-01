package com.part2.comarket.member.controller.request;

import org.springframework.util.Assert;

public record CreateMemberRequest(String email, String phoneNumber, String password, String name, String secretKey) {
    public CreateMemberRequest {
        Assert.notNull(email, "email은 필수입니다.");
        Assert.notNull(phoneNumber, "핸드폰번호는 필수입니다.");
        Assert.notNull(password, "비밀번호는 필수입니다.");
        Assert.notNull(name, "이름은 필수입니다.");
        Assert.notNull(secretKey, "secretKey는 필수입니다.");
    }
}
