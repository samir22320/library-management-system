package com.samir.librarymanagement.repository;

import com.samir.librarymanagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String memberEmail);
    boolean existsByEmail(String email);

    boolean existsMemberByEmail(String email);
}
