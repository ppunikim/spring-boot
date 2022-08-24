package com.callor.book.persistence;

import com.callor.book.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

//Generic 은 Class type 만 가능하다.
public interface UserRoleDao extends JpaRepository<UserRole, Long> {

}
