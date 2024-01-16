package com.encore.basic.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//mybatis레파지토리로 쓰겠다라는 어노테이션
@Mapper
@Repository
public interface MybatisMemberRepository extends MemberRepository{
//    본문에 mybatisRepository에서 사용할 메서드 명세를 정의해야하나,
//    MemberRepository에서 상속받고 있으므로, 생략가능
//    실질적인 쿼리 등 구현은 resources/mapper/MemberMapper.xml파일에서 수행
}
