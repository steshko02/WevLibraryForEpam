package com.asqint.webLib.controller;

import com.asqint.webLib.domain.Book;
import com.asqint.webLib.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class BookListController {
    private final BookRepo bookRepo;

    public BookListController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/bookList")
    public String main(Map<String, Object> model){
        Iterable<Book> books = bookRepo.findAll();
        model.put("books",books);
        return "bookList";
    }
}
