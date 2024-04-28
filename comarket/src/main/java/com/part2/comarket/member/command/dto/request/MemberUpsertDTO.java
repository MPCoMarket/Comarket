package com.part2.comarket.member.command.dto.request;

import com.part2.comarket.member.command.domain.Member;

public record MemberUpsertDTO(
        String email,
        String phoneNumber,
        String password,
        String name,
        String secretKey

) {
    public Member toEntity() {
        return new Member(email, phoneNumber, password, name);
    }
}
