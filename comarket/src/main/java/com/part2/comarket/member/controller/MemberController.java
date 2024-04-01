package com.part2.comarket.member.controller;

import com.part2.comarket.member.command.application.MemberService;
import com.part2.comarket.member.command.domain.Member;
import com.part2.comarket.member.controller.request.CreateMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    // 생성자를 통한 MemberService 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Void> createMember(
            @RequestBody CreateMemberRequest request) {
//      멤버 생성로직 캡슐화 필요
        memberService.register(new Member(request.email(), request.secretKey(), request.password(), request.name()));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
