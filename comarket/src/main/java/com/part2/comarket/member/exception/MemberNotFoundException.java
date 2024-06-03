package com.part2.comarket.member.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(Long memberId) {
        super("Member with ID " + memberId + " not found");
    }
}
