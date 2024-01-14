package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    @GetMapping("members")
    public String members(){
        return "/member/member-list";
    }

    @GetMapping("/member/create-screen")
    public String createScreen(){
        return "/member/member-create";
    }

    @PostMapping("/member/create")
    @ResponseBody
    public String memberCreate(@RequestBody Member member){
        System.out.println(member);
        return "member";
    }

    @GetMapping("/member/create-clear")
    public String createClear(){
        return "create";
    }
}
