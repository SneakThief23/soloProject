package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Claim;
import com.SoloProject.solo.models.Member;
import com.SoloProject.solo.repos.MemberRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }
    public List<Member> getMemberByMemberId(UUID memberId) {
        return memberRepo.findByMemberId(memberId);
    }

    // Update existing Member
    public Optional<Member> updateMember(UUID id, Member newData) {
        return memberRepo.findById(id).map(existing -> {
            if (newData.getFirstName () != null)existing.setFirstName(newData.getFirstName());
            if (newData.getEmail () != null)existing.setEmail(newData.getEmail());
            if (newData.getLastName () != null)existing.setLastName(newData.getLastName());
            if (newData.getDateOfBirth () != null)existing.setDateOfBirth(newData.getDateOfBirth());
            if (newData.getEmail () != null)existing.setEmail(newData.getEmail());
            if (newData.getPhone () != null)existing.setPhone((newData.getPhone()));
            if (newData.getMailingAddress () != null)existing.setMailingAddress(newData.getMailingAddress());
            if (newData.getEnrollments () != null)existing.setEnrollments(newData.getEnrollments());
            return memberRepo.save(existing);
        });
    }

    // Create a new user
    public Member createMember(Member member) {
        return memberRepo.save(member);
    }

    // Delete a user by ID
    public boolean deleteMember(UUID id) {
        if (memberRepo.existsById(id)) {
            memberRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Member> getAllMember() {
        return memberRepo.findAll();
    }
}
