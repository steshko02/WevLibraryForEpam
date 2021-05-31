package by.steshko.LIb.controller;

import by.steshko.LIb.api.UserService;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        boolean registered = true;
        if(userServiceImpl.ifExistByEmail(user.getEmail()) ||
                userServiceImpl.ifExistByName(user.getUsername())) {
           model.put("usernameMessage", "User exists");
           registered = false;
        }
        if(user.getUsername().length()<4) {
            model.put("usernameMessage", "Username is too short");
            registered = false;
        }
        if(user.getPassword().length()<4) {
            model.put("passMessage", "Password is too short");
            registered = false;
        }
        if(!user.getPassword2().equals(user.getPassword())) {
            model.put("pass2Message", "Password mismatch");
            registered = false;
        }
        if(!userServiceImpl.addUser(user) || !registered) {
            return "registration";
        }
        else {
            model.put("messageGood", "Successful registration! Please check your email to activate the account.");
            return "login";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(Map<String, Object> model, @PathVariable String code) {
        boolean isActivated = userServiceImpl.activateUser(code);
        if(isActivated) {
            model.put("messageGood", "Your account has been successfully activated");
        }
        else {
            model.put("messageBad", "Activation code is not found");
        }
        return "login";
    }
}
