package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberResponseDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    private final List<Member> memberDB; // 데이터 베이스를 리스트 형태로 선언

    static int total_id;

    public MemoryMemberRepository(){
        memberDB = new ArrayList<>(); // 리스트 초기화 // 데이터 담기려면 화면에서 데이터 입력
    }
//    1) List<Member> 리턴
//    2) Member등록 메서드

    @Override
    public List<Member> findAll(){
        return memberDB;
    }

    @Override
    public Member save(Member member){
        total_id +=1;
        LocalDateTime now = LocalDateTime.now();
        member.setId(total_id);
        member.setCreate_time(now);
        memberDB.add(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
        for(Member member : memberDB) {
            if (member.getId() == id) { // memberDB에서 가져온 id와 상세보기 누른 항목의 id 값을 비교
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }
}
