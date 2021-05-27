package by.steshko.LIb.controller.edit;

import by.steshko.LIb.domain.User;
import by.steshko.LIb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")

public class EditEmailController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile/editEmail")
    public String getUser(Map<String, Object> model, @AuthenticationPrincipal User user) {
        model.put("username",user.getUsername());
        model.put("email",user.getEmail());
        return "editEmail";
    }

    @PostMapping("/profile/editEmail")
    public String editUserInfo(@AuthenticationPrincipal User user,
                               Map<String, Object> model,
                               @RequestParam String oldPassword,
                               @RequestParam String newEmail) {

        boolean edit = true;

        if(!passwordEncoder.matches(oldPassword,user.getPassword())) {
            model.put("oldPassMessage", "Password is not true.");
            edit = false;
        }
        if(userService.ifExistByName(newEmail)){
            model.put("emailMessage", "This email is already taken.");
            edit = false;
        }
        if(newEmail.equals(user.getEmail())) {
            model.put("emailMessage", "Changed email.");
            edit = false;
        }
        if(edit) {
            userService.updateEmail(user,newEmail);
            model.put("messageGood", "Successful! Please check your email to activate the account.");

            return "redirect:/logout";
        }
        else return  "editEmail";
    }
}
