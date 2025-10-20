package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.Address;
import com.SoloProject.solo.models.Member;
import com.SoloProject.solo.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMember() {
        return memberService.getAllMember();
    }

    @GetMapping("/user/{id}")
    public List<Member> getMemberByUser(@PathVariable("id") UUID id) {
        return memberService.getMemberByMemberId(id);
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member created = memberService.createMember(member);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable UUID id, @RequestBody Member member){
        Optional<Member> updated = memberService.updateMember(id, member);
        return updated
                .map(acc -> ResponseEntity.ok(acc))
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a member address
    @PutMapping("/{memberId}/address")
    public ResponseEntity<Member> updateAddress(
            @PathVariable UUID memberId,
            @RequestBody Address address) {
        return memberService.updateMemberAddress(memberId, address)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Get member with their address
    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable UUID memberId) {
        return memberService.getMemberById(memberId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete member address
    @DeleteMapping("/{memberId}/address")
    public ResponseEntity<Member> deleteAddress(@PathVariable UUID memberId) {
        return memberService.deleteMemberAddress(memberId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable UUID id) {
        boolean deleted = memberService.deleteMember(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
