package com.asqint.webLib.controller;

import com.asqint.webLib.domain.Book;
import com.asqint.webLib.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class FindController {

    @Autowired
    BookRepo bookRepo;

    @GetMapping("filter")
    public String find(@RequestParam String filter,  Map<String, Object> model) {
        List<Book> booksByName;
        if(!filter.isEmpty()) {
            booksByName = bookRepo.findByName(filter);
        }
        else {
            booksByName= (List<Book>) bookRepo.findAll();
        }

        model.put("books",booksByName);
        return "bookList";
    }
}
