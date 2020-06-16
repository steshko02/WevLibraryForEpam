package com.asqint.webLib.controller;

import com.asqint.webLib.domain.Book;
import com.asqint.webLib.domain.User;
import com.asqint.webLib.repos.BookRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserLibController {
    private final BookRepo bookRepo;

    public UserLibController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/userLib")
    public String lib(@AuthenticationPrincipal User user, Map<String, Object> model) {
        List<Long> libBooksId = user.getLibBooksId();
        List<Book> books = new ArrayList<>();
        for(Long id:libBooksId) {
            books.add(bookRepo.findById(id).orElse(new Book()));
        }
        model.put("books",books);
        return ("userLib");
    }

}
