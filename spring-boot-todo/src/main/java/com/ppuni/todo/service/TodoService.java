package com.ppuni.todo.service;

import com.ppuni.todo.model.TodoVO;
import org.springframework.stereotype.Component;

import java.util.List;

public interface TodoService {

    public List<TodoVO> selectAll();
    public TodoVO findById(long seq);
    public int insert(TodoVO todoVO);
    public int update(TodoVO todoVO);
    public TodoVO delete(long seq);

}
