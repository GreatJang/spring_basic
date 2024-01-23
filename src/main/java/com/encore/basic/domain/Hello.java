package com.encore.basic.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@Data // Getter, Setter 및 toString, equals등 사전 구현
@NoArgsConstructor
public class Hello {
    private String name;
    private String email;
    private String password;

//    Builder패턴 직접 구현
    public Hello(MyBuilder myBuilder){
        this.name = myBuilder.name;
        this.email = myBuilder.email;
        this.password = myBuilder.password;
    }
    public static MyBuilder builder(){
        return new MyBuilder();
    }

    public static class MyBuilder{
        private String name;
        private String email;
        private String password;

        public MyBuilder name(String name){
            this.name = name;
            return this;
        }
        public MyBuilder email(String email){
            this.email = email;
            return this;
        }
        public MyBuilder password(String password){
            this.password = password;
            return this;
        }
        public Hello build(){
            return new Hello(this);
        }
    }
}
