package by.steshko.LIb.controller;
import by.steshko.LIb.api.BookService;
import by.steshko.LIb.api.UserService;
import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.service.BookServiceImpl;
import by.steshko.LIb.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class UserLibController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private BookService bookServiceImpl;

    @GetMapping("/userLib")
    public String lib(@AuthenticationPrincipal User user, Map<String, Object> model) {
        List<Long> libBooksId = user.getLibBooksId();
        List<Book> books = new ArrayList<>();
        for(Long id:libBooksId) {
            books.add(bookServiceImpl.findById(id));
        }
        model.put("books",books);
        return ("userLib");
    }
    @PostMapping("/userLib/delete")
    public String delete(@AuthenticationPrincipal User user,@RequestParam Long bookId) {
        user.getLibBooksId().remove(bookId);
        userServiceImpl.save(user);
        return "redirect:/userLib";
    }
}
