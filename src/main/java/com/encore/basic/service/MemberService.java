package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberService {
//    인터페이스 정의해서 사용 : 다른 DB Repository(jdbc,jpa)로 갈아서 사용할때 편의.
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemoryMemberRepository memoryMemberRepository){
        this.memberRepository = memoryMemberRepository;
    }

    public List<MemberResponseDto> findAll(){ //⭐⭐중요패턴 코드 외우기
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for(Member member : members){
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(member.getId());
            memberResponseDto.setName(member.getName());
            memberResponseDto.setEmail(member.getEmail());
            memberResponseDtos.add(memberResponseDto);

        }
        return memberResponseDtos;
    }

    public Member save(MemberRequestDto memberRequestDto){
        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
        memberRepository.save(member);
        return member;
    }
    public MemberResponseDto findById(int id) throws NoSuchElementException{ //예외처리관련해서 http로 에러메세지를 던져주어야한다. // 자바에서 발생하는 에러는 500으로 처리됌.
        // 하지만 발생하는 에러는 자바에서 http헤더로 상태코드 넣지 않으면 의미없다. 해당하는 에러에 맞게 멘트나 예외처리를 해주어야한다.
        Member member =  memberRepository.findById(id).orElseThrow(NoSuchElementException::new); // 해당 값이 있으면 정상진행 // 없으면 예외처리 // 예외처리가 강제는 아니다.
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setPassword(member.getPassword());
        memberResponseDto.setCreate_time(member.getCreate_time());
        return memberResponseDto;
    }

}
