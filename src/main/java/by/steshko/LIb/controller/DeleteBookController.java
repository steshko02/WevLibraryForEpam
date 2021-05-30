package by.steshko.LIb.controller;

import by.steshko.LIb.api.BookService;
import by.steshko.LIb.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class DeleteBookController {
    @Autowired
    private BookService bookServiceImpl;

    @PostMapping("/admin/bookList/delete")
    public String delete(@RequestParam Long bookId,
                         Map<String, Object> model) {

        bookServiceImpl.deleteBook(bookId);
        return "redirect:/bookList";
    }
}
