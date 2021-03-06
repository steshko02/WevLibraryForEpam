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
public class UserOrdersController {
    @Autowired
    private BookService bookServiceImpl;
    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/userOrders")
    public String userOrders(@AuthenticationPrincipal User user,
                             Map<String, Object> model
    ) {
        List<Book> books = new ArrayList<>();
        for(Long id:user.getOrderedBooksId()) {
            books.add(bookServiceImpl.findById(id));
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
        userServiceImpl.save(user);
        List<Book> books = new ArrayList<>();
        for(Long id:user.getOrderedBooksId()) {
            books.add(bookServiceImpl.findById(id));
        }
        model.put("books",books);
        return "userOrders";
    }
}
