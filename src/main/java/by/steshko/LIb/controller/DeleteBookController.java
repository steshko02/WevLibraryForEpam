package by.steshko.LIb.controller;

import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.BookRepo;
import by.steshko.LIb.repos.UserRepo;
import by.steshko.LIb.service.BookService;
import by.steshko.LIb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class DeleteBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;

    @PostMapping("/admin/bookList/delete")
    public String delete(@RequestParam Long bookId,
                         Map<String, Object> model) {

        bookService.tryDeleteBook(bookId);
        return "redirect:/bookList";
    }
}
