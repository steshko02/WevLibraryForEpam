package com.asqint.webLib.service;

import com.asqint.webLib.domain.Role;
import com.asqint.webLib.domain.User;
import com.asqint.webLib.repos.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, MailSender mailSender, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean addUser(User user){
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        sendMessage(user);
        return true;
    }

    public  void sendMessage(User user){
        String message = String.format(
                "Hello,%s\n"+
                        "Welcome to WebLib!\n"+
                        "To activate your account visit this link: http://localhost:8080/activate/%s",
                user.getUsername(),user.getActivationCode()
        );
        mailSender.send(user.getEmail(), "Activation code", message);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if(user==null){
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }

    public boolean updateProfile(User user, String password, String email) {

      String userEmail  = user.getEmail();
        boolean isEmailChanged = (email !=null && !email.equals(userEmail)) ||
                (userEmail !=null && !userEmail.equals(email));

        if(isEmailChanged){
            user.setEmail(email);
            if(!StringUtils.isEmpty(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if(!StringUtils.isEmpty(password)){
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setActive(false);
        userRepo.save(user);
        if(isEmailChanged) {
            sendMessage(user);
            return true;
        }
        return false;
    }
}
