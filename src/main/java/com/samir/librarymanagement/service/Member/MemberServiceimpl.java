package com.samir.librarymanagement.service.Member;

import com.samir.librarymanagement.dto.Member.MemberRequest;
import com.samir.librarymanagement.dto.Member.MemberResponse;
import com.samir.librarymanagement.entity.Member;
import com.samir.librarymanagement.execption.ResourceNotFoundException;
import com.samir.librarymanagement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceimpl implements MemberService{
    public final MemberRepository memberRepository;

    public MemberServiceimpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final MemberResponse mapToDto(Member member)
    {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }

    private void TransferData (Member member , MemberRequest memberRequest)
    {
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
    }


    @Override
    public MemberResponse CreateMember(MemberRequest memberRequest) {
        if (memberRepository.existsMemberByEmail((memberRequest.getEmail()))) {
            throw new RuntimeException("This email is already used before: " + memberRequest.getEmail());
        }
        Member member = new Member();
        TransferData(member,memberRequest);
        Member savedMember = memberRepository.save(member);
        return mapToDto(savedMember);

    }

    @Override
    public List<MemberResponse> getAllMember() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail()
        )).toList();
    }

    @Override
    public MemberResponse getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () ->  new ResourceNotFoundException("Member Id Not Founded"+memberId));
        return mapToDto(member);
    }

    @Override
    public MemberResponse UpdateMemberById(Long memberId, MemberRequest memberRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member Id Not Founded"+memberId));
        TransferData(member,memberRequest);
        Member savedMember = memberRepository.save(member);
        return mapToDto(savedMember);
    }

    @Override
    public void deleteMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()-> new ResourceNotFoundException("Member Id Not Founded"+memberId));
        memberRepository.delete(member);
    }

    @Override
    public MemberResponse getMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email Not Founded"+email));
        return mapToDto(member);
    }


}
