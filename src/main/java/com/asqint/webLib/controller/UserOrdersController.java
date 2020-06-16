package com.asqint.webLib.controller;

import com.asqint.webLib.domain.Book;
import com.asqint.webLib.domain.User;
import com.asqint.webLib.repos.BookRepo;
import com.asqint.webLib.repos.UserRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserOrdersController {
    private final BookRepo bookRepo;
    private final UserRepo userRepo;

    public UserOrdersController(BookRepo bookRepo, UserRepo userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/userOrders")
    public String userOrders(@AuthenticationPrincipal User user,
                             Map<String, Object> model
    ) {
        List<Book> books = new ArrayList<Book>();
        for(Long id:user.getOrderedBooksId()) {
            books.add(bookRepo.findById(id).orElse(new Book()));
        }
        model.put("books",books);
        return "userOrders";
    }

    @PostMapping("/userOrders")
    public String denyOrder(@AuthenticationPrincipal User user,
                            @RequestParam Long bookId,
                            Map<String, Object> model
    ) {
        List<Long> booksId = user.getOrderedBooksId();
        booksId.remove(bookId);
        userRepo.save(user);
        List<Book> books = new ArrayList<Book>();
        for(Long id:user.getOrderedBooksId()) {
            books.add(bookRepo.findById(id).orElse(new Book()));
        }
        model.put("books",books);
        return "userOrders";
    }
}
