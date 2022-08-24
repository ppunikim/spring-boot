package com.callor.book.impl;

import com.callor.book.model.UserRole;
import com.callor.book.model.UserVO;
import com.callor.book.persistence.UserDao;
import com.callor.book.persistence.UserRoleDao;
import com.callor.book.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplV1 implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final UserRoleDao userRoleDao;

    public UserServiceImplV1(PasswordEncoder passwordEncoder, UserDao userDao, UserRoleDao userRoleDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }

    /*
      회원가입을 최초 실행할 경우, ROLE_ADMIN 과 ROLE_USER 를 설정하고 , enabled 등을 true 로 설정한다.
      두 번째 이후 회원가입 경우는 ROLE_USER 만 저장하고, enable 는 false 로 설정한다.
    */

    @Transactional
    @Override
    public UserVO join(UserVO userVO) {
        //tbl_users table 에 데이터 갯수 확인
        Long userCount = userDao.count();
        List<UserRole> roleList = new ArrayList<>();

        /* UserCount < 1 로 하는 이유
           : UserCount == 0 으로 했을 때,
                    만약 아무것도 없는 데이터를 -1로 return 하면 적용되지 않기 때문에
                    그 만약을 대비해 1보다 작은 것으로 설정해준 것이다.
         */
        if(userCount < 1){
            //Legacy 에서는 선택적으로 사용을 했으나, boot 에서는 필수적 체크항목이므로 설정해줘야 한다.
            userVO.setEnabled(true);
            userVO.setAccountNonExpired(true);
            userVO.setAccountNonLocked(true);
            userVO.setCredentialsNonExpired(true);

            roleList.add(UserRole.builder().username(userVO.getUsername()).rolename("ROLE_ADMIN").build());
            roleList.add(UserRole.builder().username(userVO.getUsername()).rolename("ROLE_USER").build());
        } else {
            userVO.setEnabled(false);
            userVO.setAccountNonExpired(true);
            userVO.setAccountNonLocked(true);
            userVO.setCredentialsNonExpired(true);

            roleList.add(UserRole.builder().username(userVO.getUsername()).rolename("ROLE_USER").build());
        }//end if

        //비밀번호 암호화 하여 저장하기
        String password = userVO.getPassword();
        String encPassword = passwordEncoder.encode(password);
        userVO.setPassword(encPassword);

        userDao.save(userVO);
        //List 에 담긴 데이터를 insert All or Update All
        userRoleDao.saveAll(roleList);

        return null;
    }

}//end class
