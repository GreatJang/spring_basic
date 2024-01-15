package com.encore.basic.controller;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


// Service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> 스프링 빈으로 등록
// 스프링 빈이란 스프링이 생성하고 관리하는 객체를 의미
// 제어의 역전(Inversion of Control) -> IOC컨테이너가 스프링빈을 관리(빈을 생성, 의존성 주입)

@Controller
//@RequiredArgsConstructor
public class MemberController {

//    @Autowired //의존성주입(DI) 방법1 => 필드주입방식 // Autowired 방법 1 사용 시 final 제외
//    싱글톤으로 만들어진 MemoryMemberRepository를 MemberService에 주입하는구나.
//    다쓰면 IOC컨테이너로 반납.

//    의존성주입방법2 => 생성자주입방식이고, ⭐⭐가장 많이 사용하는 방법.
//    장점 : final을 통해 상수로 사용가능, ⭐다형성 구현 가능, 순환참조방지
//    생성자가 1개밖에 없을때에는 Autowired 생략가능
    private final MemberService memberService; // final을 통해 변수 안정성.
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

//    의존성주입방법 3. @RequiredArgsConstructor를 이용한 방식
//    @RequiredArgsConstructor
//    @Nonnull어노테이션이 붙어있는 필드 또는 초기화되지 않은 final필드를 대상으로 생성자 생성.
//    private final MemberService memberService;

    @GetMapping("/")
    public String header(){
        return "/member/header";
    }

    @GetMapping("/member/create")
    public String memberCreateScreen(){
        return "/member/member-create-screen";
    }

    @PostMapping("/member/create")
    public String memberCreate(MemberRequestDto memberRequestDto){
        memberService.memberCreate(memberRequestDto);
//        url 리다이렉트
        return "redirect:/members";
    }

    @GetMapping("members")
    public String members(Model model){
        model.addAttribute("memberList", memberService.members());
        return "/member/member-list";
    }
    @GetMapping("/member/find")
    public String memberFind(@RequestParam(value = "id") int id, Model model){
//        memberService.findById(id);
        model.addAttribute("memberList", memberService.findById(id));
        return "/member/find";
    }

}
