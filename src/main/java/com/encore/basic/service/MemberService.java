package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service // Service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> 스프링 빈으로 등록
// 스프링 빈이란 스프링이 생성하고 관리하는 객체를 의미
// 제어의 역전(Inversion of Control) -> IOC컨테이너가 스프링빈을 관리
public class MemberService {
    private final MemoryMemberRepository memoryMemberRepository;
    static int total_id;
    public MemberService(){
        memoryMemberRepository = new MemoryMemberRepository();
    }

    public List<MemberResponseDto> members(){ //⭐⭐중요패턴 코드 외우기
        List<Member> members = memoryMemberRepository.members();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for(Member member : members){
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setName(member.getName());
            memberResponseDto.setEmail(member.getEmail());
            memberResponseDto.setPassword(member.getPassword());
            memberResponseDtos.add(memberResponseDto);

        }
        return memberResponseDtos;
    }

    public void memberCreate(MemberRequestDto memberRequestDto){
        LocalDateTime now = LocalDateTime.now();
        total_id +=1;
        Member member = new Member(total_id, memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword(), now);
        memoryMemberRepository.memberCreate(member);
    }
}
