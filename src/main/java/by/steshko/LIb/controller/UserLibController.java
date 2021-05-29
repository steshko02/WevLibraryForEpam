package by.steshko.LIb.controller;
import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.BookRepo;
import by.steshko.LIb.repos.UserRepo;
import by.steshko.LIb.service.UserService;
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
    private  BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/userLib")
    public String lib(@AuthenticationPrincipal User user, Map<String, Object> model) {
        List<Long> libBooksId = user.getLibBooksId();
        List<Book> books = new ArrayList<>();
        for(Long id:libBooksId) {
            books.add(bookRepo.findById(id).orElse(new Book()));
        }
        model.put("books",books);
        return ("userLib");
    }
    @PostMapping("/userLib/delete")
    public String delete(@AuthenticationPrincipal User user,@RequestParam Long bookId,
                         Map<String, Object> model) {

//        User userFromBD = userRepo.findById(user.getId()).orElse(new User());
        user.getLibBooksId().remove(bookId);
        userRepo.save(user);
        return "redirect:/bookList";
    }
}
