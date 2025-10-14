package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Member;
import com.SoloProject.solo.repos.MemberRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    // Update existing Member
    public Optional<Member> updateMember(UUID id, Member newData) {
        return memberRepo.findById(id).map(existing -> {
            existing.setFirstName(newData.getFirstName());
            existing.setEmail(newData.getEmail());
            existing.setLastName(newData.getLastName());
            existing.setDateOfBirth(newData.getDateOfBirth());
            existing.setEmail(newData.getEmail());
            existing.setPhone((newData.getPhone()));
            existing.setMailingAddress(newData.getMailingAddress());
            existing.setEnrollments(newData.getEnrollments());
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
}
