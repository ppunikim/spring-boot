package com.callor.data.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

// VO 클래스를 JPA의 Data Table화하기 위한 선언
@Entity //지금부터 이 클래스는 Entity 설계를 위한 도구이다 라는 어노테이션이다.
@Table(name="tbl_users", schema="bootDB")   //bootDB에 tbl_users 라는 이름으로 UserVO를 참조해 table 있다고 가정 또는 강제생성하라.
public class UserVO {

    //seq BIGINT PRIMARY KEY AUTO_INCREMENT 설정하기.
    @Id                         //seq 칼럼이 PK 이다. 라는 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL 의 auto_increment 설정을 해 seq 값을 관리해라.
    private long seq;           //a 는 attribute 라는 의미이다.

    //username VARCHAR(20) NOT NULL UNIQUE 라는 말이다.
    @Column(columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
    private String username;

    //DB 종류에 관계없이 문자열 255자로 설정해라. 그리고 NOT NULL 로 설정해라.
    @Column(length= 255, nullable = false)
    private String password;

    @Column(length = 125)
    private String email;

    @Column(length = 20)
    private String phone;

    //Column 을 지정하지 않으면 String 형의 경우, 문자열(VARCHAR) 255를 default 로 선언해준다.
    private String address;

    @Column(length=20)
    private String realname;

    @Column(length=20)
    private String nickname;


}//end class
