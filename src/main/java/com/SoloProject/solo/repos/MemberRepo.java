package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface MemberRepo extends JpaRepository<Member, UUID>{
    //Optional<List> findByFirstName(String firstName);
    //Optional<List> findByLastName(String lastName);
    //Optional<List> findByEmail(String email);
    //Optional<List> findByMailingAddress(String mailingAddress);
    List<Member> findByMemberId(UUID memberId);
}
