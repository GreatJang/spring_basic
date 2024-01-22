package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//spring data jpa의 기본기능을 쓰기 위해서는 JpaRepository를 상속해야함
//상속시에 entity명과 해당 entity의 pk타입을 명시

//클래스는 객체를 만들 수 있다.
//interface 객체를 만들려면 즉시 오버라이딩
//SpringDataJpaMemberRepository는 인터페이스
//이 인터페이스 MemberRepository, JpaRepository를 모두 상속
//class 다중상속 불가능하지만 interface 다중상속 가능
//구현체를 repository, extends, JpaRepository를 쓰면 springdatajpa기술을 통해 주입됌

//실질적인 구현클래스와 스펙은simpleJpaRepository에 있고
//실질적인 구동상황에서 hibernate구현체에 동작을 위임.

public interface SpringDataJpaMemberRepository extends MemberRepository, JpaRepository<Member, Integer> {
//    JpaRepository<Member, Integer> 왼쪽 = 엔티티 오른쪽 =
}
