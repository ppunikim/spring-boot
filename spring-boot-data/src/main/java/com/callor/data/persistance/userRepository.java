package com.callor.data.persistance;

import com.callor.data.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

// spring data(JPA)에서는 JpaRepository 를 Generic 방식으로 상속받아 사용한다.
public interface userRepository extends JpaRepository<UserVO, Long> {
    //GenericDao 와 같은 역할을 한다.

    //ctrl+ o : 상속받아 사용하는 것.




}//end class
