package com.ppuni.todo.persistance;

import com.ppuni.todo.model.TodoVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoDao extends JpaRepository<TodoVO, Long> {

}
