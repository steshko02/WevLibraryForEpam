package by.steshko.LIb.controller;

import by.steshko.LIb.api.BookService;
import by.steshko.LIb.domain.Book;
import by.steshko.LIb.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class FindController {
    @Autowired
    BookService bookServiceImpl;
    @GetMapping("filter")
    public String find(@RequestParam String filter,  Map<String, Object> model) {
        List<Book> booksByName;
        if(!filter.isEmpty())
            booksByName = bookServiceImpl.findByName(filter);
        else
            booksByName= (List<Book>) bookServiceImpl.getAll();
        model.put("books",booksByName);
        return "bookList";
    }
}
