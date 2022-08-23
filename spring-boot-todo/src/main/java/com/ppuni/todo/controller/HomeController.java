package com.ppuni.todo.controller;

import com.ppuni.todo.model.TodoVO;
import com.ppuni.todo.persistance.TodoDao;
import com.ppuni.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class HomeController {
private final TodoService todoService;
    public HomeController(TodoService todoService) {

        this.todoService = todoService;
    }
//Dao 를 바로 호출하기보다, Service 를 호출해서 안정성을 높여준다.
// 또한 service 에서 새로운 코드를 더 작성할 수 있으므로 Dao 를 바로 호출하지 말자.
    @RequestMapping(value = "/",method= RequestMethod.GET)
    public String home(Model model){
        List<TodoVO> todoList = todoService.selectAll();
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
        todoService.insert(todoVO);

        return "redirect:/";
    }
    @RequestMapping(value="/{seq}/delete",method = RequestMethod.GET)
    public String delete(TodoVO todoVO,@PathVariable("seq") long seq){
        todoService.delete(seq);

        return "redirect:/";
    }

}
