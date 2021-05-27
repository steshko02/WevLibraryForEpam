package by.steshko.LIb.controller;

import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.BookRepo;
import by.steshko.LIb.repos.UserRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BookPageController {
    private final UserRepo userRepo;
    private final BookRepo bookRepo;

    public BookPageController(UserRepo userRepo, BookRepo bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    @GetMapping("/bookPage")
    public String bookPage(@RequestParam Long bookId, Map<String, Object> model) {
        Book book = bookRepo.findById(bookId).orElse(new Book());
        model.put("book", book);
        return "bookPage";
    }

    @PostMapping("/bookPage")
    public String orderBook(@AuthenticationPrincipal User user,
                            @RequestParam Long bookId,
                            Map<String, Object> model
    ) {
        if(!user.getOrderedBooksId().contains(bookId)) {
            user.addOrderedBook(bookId);
            userRepo.save(user);
        }
        return "redirect:/bookList";
    }
}
