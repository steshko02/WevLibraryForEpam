package by.steshko.LIb.controller;
import by.steshko.LIb.api.BookService;
import by.steshko.LIb.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class BookListController {
    @Autowired
    private BookService bookServiceImpl;

    @GetMapping("/bookList")
    public String main(Map<String, Object> model){
        model.put("books", bookServiceImpl.getAll());
        return "bookList";
    }
}
