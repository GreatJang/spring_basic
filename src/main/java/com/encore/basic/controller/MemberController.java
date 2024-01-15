package com.encore.basic.controller;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService; // 최초세팅하고 그 이후에는 수정 불가 상수로 세팅
    MemberController(){
        memberService = new MemberService(); // 객체 만듦
    }
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
        return "/member/header";
    }

    @GetMapping("members")
    public String members(Model model){
        model.addAttribute("memberList", memberService.members());
        return "/member/member-list";
    }
}
