package com.samir.librarymanagement.service.Member;

import com.samir.librarymanagement.dto.Member.MemberRequest;
import com.samir.librarymanagement.dto.Member.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse CreateMember(MemberRequest memberRequest);

    List<MemberResponse> getAllMember();

    MemberResponse getMemberById(Long memberId);

    MemberResponse UpdateMemberById(Long memberId, MemberRequest memberRequest);

    void deleteMemberById(Long memberId);

    MemberResponse getMemberByEmail(String memberEmail);
}
