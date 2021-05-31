package by.steshko.LIb.controller;

import by.steshko.LIb.api.BookService;
import by.steshko.LIb.api.UserService;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminOrdersController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private BookService bookServiceImpl;


    @GetMapping("/adminOrders")
    public String adminOrders(Map<String, Object> model) {
        model.put("hash", bookServiceImpl.getAllUsersAndOrders());
        return "adminOrders";
    }

    @PostMapping("/adminOrders/allow")
    public String allow(@RequestParam Long userId, @RequestParam Long bookId) {
        User user =  userServiceImpl.findById(userId);
        List<Long> libBooksId = user.getLibBooksId();
        List<Long> orderedBooksId = user.getOrderedBooksId();
        libBooksId.add(bookId);
        orderedBooksId.remove(bookId);
        user.setOrderedBooksId(orderedBooksId);
        user.setLibBooksId(libBooksId);
        userServiceImpl.save(user);
        return "redirect:/adminOrders";
    }

    @PostMapping("/adminOrders/deny")
    public String deny(@RequestParam Long userId, @RequestParam Long bookId, Map<String, Object> model) {
        User user = userServiceImpl.findById(userId);
        List<Long> orderedBooksId = user.getOrderedBooksId();
        orderedBooksId.remove(bookId);
        user.setOrderedBooksId(orderedBooksId);
        userServiceImpl.save(user);
        return "redirect:/adminOrders";
    }
}