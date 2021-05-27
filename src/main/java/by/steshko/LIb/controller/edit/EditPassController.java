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
public class EditPassController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile/editPass")
    public String getUser(Map<String, Object> model, @AuthenticationPrincipal User user) {
        model.put("username",user.getUsername());
        return "editPass";
    }

    @PostMapping("/profile/editPass")
    public String editUserInfo(@AuthenticationPrincipal User user,
                               Map<String, Object> model,
                               @RequestParam String oldPassword,
                               @RequestParam String password,
                               @RequestParam String password2 ) {

        boolean edit = true;

        if(!passwordEncoder.matches(oldPassword,user.getPassword())) {
            model.put("oldPassMessage", "Password is not true.");
            edit = false;
        }
        if(passwordEncoder.matches(password,user.getPassword()) &&
                passwordEncoder.matches(password2,user.getPassword())) {
            model.put("passMessage", "Change password!");
            edit = false;
        }
        if(password.length()<4) {
            model.put("passMessage", "Password is too short");
            edit = false;
        }
        if(!password2.equals(password)) {
            model.put("pass2Message", "Password mismatch");
            edit = false;
        }
        if(edit) {
            userService.updatePassword(user, password,oldPassword);
            model.put("messageGood", "Successful! Please check your email to activate the account.");

            return "redirect:/logout";
        }
        else return  "editPass";
    }
}