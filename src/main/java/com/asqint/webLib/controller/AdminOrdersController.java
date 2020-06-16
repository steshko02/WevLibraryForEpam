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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminOrdersController {
    private final UserRepo userRepo;
    private final BookRepo bookRepo;
    public AdminOrdersController(UserRepo userRepo, BookRepo bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    @GetMapping("/adminOrders")
    public String adminOrders(Map<String, Object> model) {
        Iterable<User> users = userRepo.findAll();
        Map<User, List<Book>> hash = new HashMap<>();
        for(User user:users) {
            List<Long> booksId = user.getOrderedBooksId();
            List<Book> books = new ArrayList<>();
            if(!booksId.isEmpty()) {
                for(Long id:booksId) {
                    books.add(bookRepo.findById(id).orElse(new Book()));
                }
                hash.put(user,books);
            }
        }
        model.put("hash",hash);
        return "adminOrders";
    }

    @PostMapping("/adminOrders/allow")
    public String allow(@RequestParam Long userId, @RequestParam Long bookId, Map<String, Object> model) {
        User user = userRepo.findById(userId).orElse(new User());
        List<Long> libBooksId = user.getLibBooksId();
        List<Long> orderedBooksId = user.getOrderedBooksId();
        libBooksId.add(bookId);
        orderedBooksId.remove(bookId);
        user.setOrderedBooksId(orderedBooksId);
        user.setLibBooksId(libBooksId);
        userRepo.save(user);
        return "redirect:/adminOrders";
    }

    @PostMapping("/adminOrders/deny")
    public String deny(@RequestParam Long userId, @RequestParam Long bookId, Map<String, Object> model) {
        User user = userRepo.findById(userId).orElse(new User());
        List<Long> orderedBooksId = user.getOrderedBooksId();
        orderedBooksId.remove(bookId);
        user.setOrderedBooksId(orderedBooksId);
        userRepo.save(user);
        return "redirect:/adminOrders";
    }
}
