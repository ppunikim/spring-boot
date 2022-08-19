package com.callor.book.controller;

import com.callor.book.model.BookVO;
import com.callor.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

    private final BookService bookService;
    public HomeController(BookService bookService) {
        this.bookService = bookService;
    } //alt+insert

    @RequestMapping(value="/" , method = RequestMethod.GET)
    public String home(@RequestParam(name="code",required=false,defaultValue = "") String isbn,
                       Model model){
        List<BookVO> bookList = bookService.selectAll();

        if(isbn != null || !isbn.isEmpty()){
            BookVO bookVO = bookService.findById(isbn);
            model.addAttribute("BOOK",bookVO);
            log.debug("findByData{}", bookVO);
        }
        model.addAttribute("BOOKS",bookList);
        return "home";

    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String home(BookVO bookVO){
        log.debug("도서정보 {}", bookVO);
        bookService.insert(bookVO);
        return "redirect:/";
    }

}//end class
