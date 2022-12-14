package com.callor.book.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
// 이것은 OneToMany 와 충돌하여 오류가 생긴다.
@Builder

@Entity
@Table(name = "tbl_users")
public class UserVO implements UserDetails {

    @Id
    private String username;

    private String password;
    private boolean enabled;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    private String email;
    private String realname;

    /* Transient
       이 칼럼은 table 을 생성할 때 만들지 말라는 것이다.
     */
    @Transient
    Collection<? extends GrantedAuthority> authorities;

    /*  @OneToMany : tbl_authorities table 과 1:N 관계를 선언하기
        이 선언을 하게 되면 자동으로 tbl_users table 을  SELECT 할 때
        tbl_authorities table 도 같이 SELECT 하여 하나의 묶음으로 만든다.

        UserVO(tbl_users) 와 UserRole(tbl_authorities) table 간에
        FK 설정이 자동으로 형성된다.
        CascadeType.REMOVE : tbl_users 테이블에서 데이터를 DELETE 하면
        tbl_authorities 테이블에 연관된 데이터도 같이 삭제해라

        tbl_users table 을 SELECT 하고, 시간을 지연시킨 후,
        tbl_authorities table 을 SELECT
        fetch= FetchType.LAZY

        List<UserRoles> type 으로 지정해도 되는데 Set<UserRoles> type 으로 정한 이유
        : List 와 Set 은 성질이 많이 비슷하다. 하지만 Set 은 List 에 비해 검색속도가 매우 빠르다.
          Set 은 중복된 데이터를 허용하지 않는다. 만약 중복된 데이터를 add 하면 원래 있던 데이터를
          update 해버린다.
          Set<String> strSet = new HashSet<String>
          strSet.add("뿌니");
          strSet.add("뿌뿌");
          strSet.add("뿌니");

          실제 strSet 에는 2개의 데이터만 존재한다.

          Set 을 생성할 때 HashSet 을 사용하면 데이터가 add 한 순서대로 저장되지 않는다. 무작위로 함.
          add 한 데이터의 순서를 보장하려면 java 1.4 부터 지원되는
          Set<String> strSet = new LinkedHashSet<String>()을 사용하여 객체를 초기화해준다.
     */
    @OneToMany(mappedBy = "userVO",
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY)
    private Set<UserRole> userRoles;

    @Override
    public String toString() {
        return "UserVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", email='" + email + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }
}
