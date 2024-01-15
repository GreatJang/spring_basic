package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
//모든 매개변수를 넣은 생성자 AllArgsConstructor // 기본생성자는 없어진다.
@AllArgsConstructor
//@NoArgsConstructor //기본생성자 생성
// setter가 들어가면 언제든지 값을 set할 수 있기때문에 불안해짐.
// 그럴때 Dto를 두어
// 일단 엔티티(DB에 들어가는 값)와 분리하기위하여 Dto 사용
// Dto는 사용자와 상호작용 역할

public class Member {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime create_time;
}
