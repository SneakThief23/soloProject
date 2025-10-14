package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.Member;
import com.SoloProject.solo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/member")

public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService){ this.memberService = memberService;}

    @GetMapping
    public List<Member> getAllMember() {
        return memberService.getAllMember();
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }


}
