package com.callor.book.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tbl_books", schema = "bootDB")
public class BookVO {
    /* 도서정보 관리를 위해 객체 추상화 하기
       : 도서명, 출판사, 저자, 발행연도, 가격 등 항목이 필요하다.
       이러한 항목을 기준으로 VO 클래스를 디자인하자.
     JPA 의 Entity 로 등록하고, 물리적 table 구성하기
     */
    @Id                     //VO 클래스를 Entity 로 등록하면, 반드시 PK 를 설정해줘야 한다.
    @Column(length = 13)    //고정된 문자열은 이렇게 써준다.
    private String isbn;    //도서 코드

    @Column(length = 125, nullable = false)
    private String title;   //도서명

    private String comp;    //출판사

    private String author;  //저자

    @Column(length = 10)    //2022-09-16 이런식으로 10자리만 사용할 것이다.
    private String pubdate; //출판일


    /*
        변수형이 int 형일 경우, 기본값이 not null 로 설정되므로
        임의로 nullable 을 true 로 하여 not null 을 해제한다.
     */
    @Column(nullable = true)
    private int price;      //정가
}
