package com.part2.comarket.member.controller;

import com.part2.comarket.member.command.application.MemberService;
import com.part2.comarket.member.command.dto.request.MemberUpsertDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Void> createMember(
            @RequestBody MemberUpsertDTO request) {
//      멤버 생성로직 캡슐화 필요
        memberService.registerMember(request.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long id,
            @RequestBody MemberUpsertDTO request
    ) {
        memberService.updateMember(id, request.toEntity());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(
            @PathVariable Long id
    ) {
        memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
