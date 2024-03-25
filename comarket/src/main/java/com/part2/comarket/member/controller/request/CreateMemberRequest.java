package com.part2.comarket.member.controller.request;

import org.springframework.util.Assert;

public record CreateMemberRequest(String email, String password, String name) {
    public CreateMemberRequest {
        Assert.notNull(email, "email은 필수입니다.");
        Assert.notNull(password, "비밀번호는 필수입니다.");
        Assert.notNull(name, "이름은 필수입니다.");
    }
}
