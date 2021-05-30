package by.steshko.LIb.controller.edit;

import by.steshko.LIb.api.UserService;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class EditNameController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile/editName")
    public String getUser(Map<String, Object> model, @AuthenticationPrincipal User user) {
        model.put("username",user.getUsername());
        return "editName";
    }
    @PostMapping("/profile/editName")
    public String editUserInfo(@AuthenticationPrincipal User user,
                               Map<String, Object> model,
                               @RequestParam String oldPassword,
                               @RequestParam String newName ) {

        boolean edit = true;

        if(!passwordEncoder.matches(oldPassword,user.getPassword())) {
            model.put("oldPassMessage", "Password is not true.");
            edit = false;
        }
        if(userServiceImpl.ifExistByName(newName)){
            model.put("nameMessage", "This name is already taken.");
            edit = false;
        }
        if(newName.length()<4) {
            model.put("nameMessage", "Name is too short");
            edit = false;
        }
        if(newName.equals(user.getUsername())) {
            model.put("nameMessage", "Change name!");
            edit = false;
        }
        if(edit) {
            userServiceImpl.updateName(user, newName);
            model.put("messageGood", "Successful! Please check your email to activate the account.");

            SecurityContextHolder.clearContext();
            return "redirect:/login";
        }
       else   return  "editName";
    }
}
