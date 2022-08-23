package com.ppuni.todo.controller;

import com.ppuni.todo.model.TodoVO;
import com.ppuni.todo.persistance.TodoDao;
import com.ppuni.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class HomeController {

    private final TodoDao todoDao;
    public HomeController(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @RequestMapping(value = "/",method= RequestMethod.GET)
    public String home(Model model){
        List<TodoVO> todoList = todoDao.findAll();
        model.addAttribute("TODO",todoList);
        return "home";
    }

    @RequestMapping(value = "/todo",method = RequestMethod.GET)
    public String todo(){
        return "todo";
    }
    @RequestMapping(value = "/todo",method = RequestMethod.POST)
    public String todo(TodoVO todoVO){
        log.debug("데이터 {}", todoVO);
        todoDao.save(todoVO);

        return "redirect:/";
    }


}
