package com.samir.librarymanagement.controller;

import com.samir.librarymanagement.dto.Member.MemberRequest;
import com.samir.librarymanagement.dto.Member.MemberResponse;
import com.samir.librarymanagement.service.Member.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    public final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.CreateMember(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMember() {
        List<MemberResponse> memberResponses = memberService.getAllMember();
        return ResponseEntity.ok(memberResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable("id") Long memberId) {
        MemberResponse memberResponse = memberService.getMemberById(memberId);
        return ResponseEntity.ok(memberResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMemberById
            (@Valid @PathVariable("id") Long memberId, @RequestBody MemberRequest memberRequest)
    {
        MemberResponse memberResponse = memberService.UpdateMemberById(memberId,memberRequest);
        return ResponseEntity.ok(memberResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable("id")Long memberId)
    {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<MemberResponse> getMemberByEmail(@PathVariable("email") String memberEmail)
    {
        MemberResponse memberResponse = memberService.getMemberByEmail(memberEmail);
        return ResponseEntity.ok(memberResponse);
    }

}
