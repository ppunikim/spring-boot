package com.callor.book.service.auth;

// import 최적화 = ctrl + alt + o
import com.callor.book.model.UserRole;
import com.callor.book.model.UserVO;
import com.callor.book.persistence.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserDetailsServiceImplV1 implements UserDetailsService {

    private final UserDao userDao;
    public UserDetailsServiceImplV1(UserDao userDao) {
        this.userDao = userDao;
    }

    //참조무결성을 유지하기 위해서는 @Transactional 으로 묶어야 한다.
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("USER 서비스: {}",username);
        UserVO userVO = userDao.findById(username).orElse(UserVO.builder().build());
         //findById 는 Optional type 의 VO 를 return // null 값 방지하기 위한 코드
        if( !userVO.getUsername().equals(username)){
            throw new UsernameNotFoundException(username +"없습니다.");
        }
        log.debug("로그인한 사용자 {}",userVO);
        Set<UserRole> roleList = userVO.getUserRoles();
        for(UserRole role : roleList){
            log.debug("사용자 ROLE 정보{}",role.getRolename());
        }

        return userVO;
    }


}//end class
