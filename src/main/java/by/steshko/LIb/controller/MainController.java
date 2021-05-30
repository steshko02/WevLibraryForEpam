package by.steshko.LIb.controller;

import by.steshko.LIb.api.BookService;
import by.steshko.LIb.domain.Book;
import by.steshko.LIb.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private BookService bookServiceImpl;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        Iterable<Book> books = bookServiceImpl.getAll();
        model.put("books",books);
        return "main";
    }
}
