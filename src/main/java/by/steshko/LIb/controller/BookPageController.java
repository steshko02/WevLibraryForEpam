package by.steshko.LIb.controller;

import by.steshko.LIb.api.BookService;
import by.steshko.LIb.api.UserService;
import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.service.BookServiceImpl;
import by.steshko.LIb.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class BookPageController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private BookService bookServiceImpl;

    @GetMapping("/bookPage")
    public String bookPage(@RequestParam Long bookId, Map<String, Object> model) {
        Book book = bookServiceImpl.findById(bookId);
        Iterable<User> users = userServiceImpl.getAll();
        for(User user:users) {
            List<Long> booksId = user.getOrderedBooksId();
            if(booksId.contains(bookId)) {
                model.put("errorDeleteID", bookId);
                break;
            }
        }
        model.put("book", book);
        return "bookPage";
    }

    @PostMapping("/bookPage")
    public String orderBook(@AuthenticationPrincipal User user,
                            @RequestParam Long bookId
    ) {
        if(!user.getOrderedBooksId().contains(bookId)) {
           User userFromBD= userServiceImpl.findById(user.getId());
            userFromBD.getOrderedBooksId().add(bookId);
            userServiceImpl.save(userFromBD);
            user.setOrderedBooksId(userFromBD.getOrderedBooksId());
        }
        return "redirect:/bookList";
    }
}
