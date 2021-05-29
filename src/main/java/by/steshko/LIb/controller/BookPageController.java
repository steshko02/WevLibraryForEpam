package by.steshko.LIb.controller;

import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.BookRepo;
import by.steshko.LIb.repos.UserRepo;
import by.steshko.LIb.service.BookService;
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
    private  UserRepo userRepo;
    @Autowired
    private  BookRepo bookRepo;
    @Autowired
    private BookService bookService;


    @GetMapping("/bookPage")
    public String bookPage(@RequestParam Long bookId, Map<String, Object> model) {
        Book book = bookRepo.findById(bookId).orElse(new Book());
        Iterable<User> users = userRepo.findAll();
        for(User user:users) {
            List<Long> booksId = user.getOrderedBooksId();
            if(booksId.contains(bookId)) {
                model.put("errorDeleteID", bookId);
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
           User userFromBD= userRepo.findById(user.getId()).orElse(new User());
            userFromBD.getOrderedBooksId().add(bookId);
            userRepo.save(userFromBD);
            user.setOrderedBooksId(userFromBD.getOrderedBooksId());
        }
        return "redirect:/bookList";
    }
}
