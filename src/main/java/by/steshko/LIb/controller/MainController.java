package by.steshko.LIb.controller;

import by.steshko.LIb.domain.Book;
import by.steshko.LIb.repos.BookRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    private final BookRepo bookRepo;

    public MainController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        Iterable<Book> books = bookRepo.findAll();
        model.put("books",books);
        return "main";
    }
}
