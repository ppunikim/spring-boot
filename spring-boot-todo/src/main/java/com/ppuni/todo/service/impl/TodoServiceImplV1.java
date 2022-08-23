package com.ppuni.todo.service.impl;

import com.ppuni.todo.model.TodoVO;
import com.ppuni.todo.persistance.TodoDao;
import com.ppuni.todo.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoServiceImplV1 implements TodoService {

    private final TodoDao todoDao;
    public TodoServiceImplV1(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public List<TodoVO> selectAll() {
        List<TodoVO> todoList = todoDao.findAll();
        return todoList;
    }

    @Override
    public TodoVO findById(long seq) {
        return null;
    }

    @Override
    public int insert(TodoVO todoVO) {
        return 0;
    }

    @Override
    public int update(TodoVO todoVO) {
        return 0;
    }

    @Override
    public TodoVO delete(long seq) {
        return null;
    }
}
