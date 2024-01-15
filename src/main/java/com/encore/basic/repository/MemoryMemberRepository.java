package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMemberRepository {
    private final List<Member> memberDB; // 데이터 베이스를 리스트 형태로 선언
    public MemoryMemberRepository(){
        memberDB = new ArrayList<>(); // 리스트 초기화 // 데이터 담기려면 화면에서 데이터 입력
    }
//    1) List<Member> 리턴
//    2) Member등록 메서드

    public List<Member> members(){
        return memberDB;
    }

    public void memberCreate(Member member){
        memberDB.add(member);

    }
}
