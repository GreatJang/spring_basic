package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// ResponseEntity : restapi에서 응답헤더값 + body

@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {

//    @ResponseStatus 어노테이션 방식
    @GetMapping("responsestatus")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus(){
        return "OK";
    }

    @GetMapping("responsestatus2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2(){
        Member member = new Member("kim", "kim@naver.com", "123123qweqwe");
        return member;
    }

//    ResponseEntity객체를 직접 생성한 방식
    @GetMapping("custom1")
    public ResponseEntity<Member> custom1(){
        Member member = new Member("kim", "kim@naver.com", "123123qweqwe");
        return new ResponseEntity<>(member, HttpStatus.CREATED); //member빠지면 상태코드만 나간다.
    }

//    ResponseEntity<String>일경우 text/html로 설정
    @GetMapping("custom2")
    public ResponseEntity<String> custom2(){
        String html = "<h1>없는 ID입니다.<h1>";
        return new ResponseEntity<>(html, HttpStatus.NOT_FOUND);
    }

//    map형태의 메시지 커스텀
    public static ResponseEntity<Map<String, Object>> customMap1(HttpStatus status, String message){
        Map<String, Object> body = new HashMap<>();
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        body.put("status", Integer.toString(status.value()));
        body.put("status message", status.getReasonPhrase());
        body.put("error message", message);
        return new ResponseEntity<>(body, status);
    }

    @GetMapping("map_custom2")
    public static ResponseEntity<Map<String, Object>> customMap2(HttpStatus status, Object object){
        Map<String, Object> body = new HashMap<>();
//        HttpStatus status = HttpStatus.CREATED;
        body.put("status", Integer.toString(status.value()));
        body.put("error message", object);
        return new ResponseEntity<>(body, status);
    }

//    메서드 체이닝 : ResponseEntity의 클래스메서드 사용
    @GetMapping("chaining1")
    public ResponseEntity<Member> chaining1(){
        Member member = new Member("kim", "kim@naver.com", "123123qweqwe");
        return ResponseEntity.ok(member);
    }

    @GetMapping("chaining2")
    public ResponseEntity<Member> chaining2(){
        return ResponseEntity.notFound().build();
    }

    @GetMapping("chaining3")
    public ResponseEntity<Member> chaining3(){
        Member member = new Member("kim", "kim@naver.com", "123123qweqwe");
//        return ResponseEntity.status(HttpStatus.CREATED).body(member);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
}
