package by.steshko.LIb.controller;

import by.steshko.LIb.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminGetUsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/userList")
    public String getUsers(Map<String, Object> model){
        model.put("users", userService.getUsers());
        return "usersInfo";
    }
    @PostMapping("/admin/block")
    public String block(Map<String, Object> model,
                        @RequestParam Long userId){

        userService.blockUsers(userId);
        return "redirect:/admin/userList";
    }
}
