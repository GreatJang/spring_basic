package com.encore.basic.domain;

import lombok.*;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
//모든 매개변수를 넣은 생성자 AllArgsConstructor // 기본생성자는 없어진다.
//@AllArgsConstructor
//@NoArgsConstructor //기본생성자 생성
// setter가 들어가면 언제든지 값을 set할 수 있기때문에 불안해짐.
// 그럴때 Dto를 두어
// 일단 엔티티(DB에 들어가는 값)와 분리하기위하여 Dto 사용
// Dto는 사용자와 상호작용 역할

//entity어노테이션을 통해 mariadb의 테이블 및 컬럼을 자동생성 //jpa
//class명은 테이블명, 변수명은 컬럼명
@Entity // 기본생성자가 꼭 필요하다. // jpa에서 컬럼 정보를 다 알고 있음
@NoArgsConstructor // 기본생성자 생성
@ToString
public class Member {
    @Setter // 메모리 DB때문에 어쩔수 없이 삽입 //실제 BD사용시에는 사용 X
    @Id //pk설정
    //Identity = auto_increment설정. auto=JPA구현체가 자동으로 적절한 키생성 전략 선택.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    String은 DB에 varchar로 변환
    @Setter
    private String name;
    @Column(nullable = false, length = 50)
    private String email;
    @Setter
    private String password;
    @Setter
    @Column(name = "created_time") // name옵션을 통해 DB의 컬럼명 별도 지정가능 //jpa
    private LocalDateTime create_time;

    public Member(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.create_time = LocalDateTime.now(); // 생성자에 바로집어넣기
    }
//    public uppdateMember(String name, String password){
//        this name = memberRequestDto.getName()
//    }
}
