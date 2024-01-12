package com.encore.basic.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Data // Getter, Setter 및 toString, equals등 사전 구현
public class Hello {
    private String name;
    private String email;
    private String password;
}
