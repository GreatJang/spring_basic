package com.encore.basic.domain;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
//모든 매개변수를 넣은 생성자 AllArgsConstructor // 기본생성자는 없어진다.
//@AllArgsConstructor
//@NoArgsConstructor //기본생성자 생성
// setter가 들어가면 언제든지 값을 set할 수 있기때문에 불안해짐.
// 그럴때 Dto를 두어
// 일단 엔티티(DB에 들어가는 값)와 분리하기위하여 Dto 사용
// Dto는 사용자와 상호작용 역할

public class Member {
    @Setter // 메모리 DB때문에 어쩔수 없이 삽입 //실제 BD사용시에는 사용 X
    private int id;
    private String name;
    private String email;
    private String password;
    @Setter
    private LocalDateTime create_time;

    public Member(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
