package com.encore.basic.controller;

import java.util.*;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Api(tags = "회원관리서비스")
@RestController // responsebody 안붙혀도 됌
@RequestMapping("/rest")
public class MemberRestController {
    private final MemberService memberService; // final을 통해 변수 안정성.
    @Autowired
    public MemberRestController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/member/create")
    public String memberCreate(@RequestBody MemberRequestDto memberRequestDto){
        memberService.save(memberRequestDto);
        return "ok";
    }

    @GetMapping("members")
    public List<MemberResponseDto> members(){
        return memberService.members();
    }
    @GetMapping("/member/find/{id}")
    public ResponseEntity<Map<String, Object>> memberFind(@PathVariable int id){
        MemberResponseDto memberResponseDto = null;
        try {
            memberResponseDto = memberService.findById(id); // 예외터지는 부분은 여기
            return ResponseEntityController.customMap2(HttpStatus.OK, memberResponseDto); //해당 정보 있으면 customMap2가서 정보가져옴
        }catch (EntityNotFoundException e){ // service에서 던진 에러메시지를 e에서 받는다. "검색하신 ID의 Member가 없습니다."
            e.printStackTrace();
            return ResponseEntityController.customMap1(HttpStatus.NOT_FOUND, e.getMessage());//해당 정보 없으면 customMap2가서 에러메시지 가져옴
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 상태코드만
        }
    }

    @DeleteMapping("/member/delete/{id}")
    public String memberDelete(@PathVariable int id){
        memberService.delete(id);
        return "ok";
    }

    @PatchMapping("/member/update") //부분수정 PATCH
    public MemberResponseDto memberUpdate(@RequestBody MemberRequestDto memberRequestDto){
        memberService.memberUpdate(memberRequestDto);
        return memberService.findById(memberRequestDto.getId());
    }
}
