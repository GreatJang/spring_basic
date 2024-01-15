package com.encore.basic.domain;

import lombok.Data;

@Data
public class MemberResponseDto {
    private String name;
    private String email;
    private String password;
}
