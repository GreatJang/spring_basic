package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository){
        this.memberRepository = springDataJpaMemberRepository;
    }

    public List<MemberResponseDto> members(){ //⭐⭐중요패턴 코드 외우기
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


//    코딩 진행 과정에서 예외상황 발생 시 코드 실행이전으로 rollback하는 역할
//    회원등록 시 kim이라는 사람이 등록 하면 예외를 발생시켜서 transactional발생 등록 이전으로 rollback된다.
//    Transactional 어노테이션 클래스 단위로 붙이면 모든 메서드에 각각 Transaction적용
//    Transactional을 적용하면 한 메서드 단위로 트랜잭션 지정
    @Transactional
    public void save(MemberRequestDto memberRequestDto) throws IllegalArgumentException{ // Controller로 예외던짐
        try {
            Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
            memberRepository.save(member);
        }catch (Exception e){
            e.printStackTrace();
        }

////        transaction테스트
//        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
//        memberRepository.save(member);
//        if(member.getName().equals("kim")){
//            throw new IllegalArgumentException();
//        }
    }

    public MemberResponseDto findById(int id) throws EntityNotFoundException{ //예외처리관련해서 http로 에러메세지를 던져주어야한다. // 자바에서 발생하는 에러는 500으로 처리됌.
        // 하지만 발생하는 에러는 자바에서 http헤더로 상태코드 넣지 않으면 의미없다. 해당하는 에러에 맞게 멘트나 예외처리를 해주어야한다.
        Member member =  memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("검색하신 ID의 Member가 없습니다.")); // 해당 값이 있으면 정상진행 // 없으면 예외처리 // 예외처리가 강제는 아니다.
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setPassword(member.getPassword());
        memberResponseDto.setCreate_time(member.getCreate_time());
        return memberResponseDto;
    }

    public void delete(int id) {
        memberRepository.delete(memberRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
    public void memberUpdate(MemberRequestDto memberRequestDto){
        Member member = memberRepository.findById(memberRequestDto.getId()).orElseThrow(EntityNotFoundException::new);
        member.updateMember(memberRequestDto.getName(),memberRequestDto.getPassword());
//        member.setName(memberRequestDto.getName());
//        member.setPassword(memberRequestDto.getPassword());
        memberRepository.save(member);
    }

}
