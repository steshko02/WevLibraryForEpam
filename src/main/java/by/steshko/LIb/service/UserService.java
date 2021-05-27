package by.steshko.LIb.service;

import by.steshko.LIb.domain.Role;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.UserRepo;
import com.sun.istack.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    public  boolean ifExistByEmail(String email){
        return userRepo.existsByEmail(email);
    }

    public  boolean ifExistByName(String name){
        return userRepo.existsByUsername(name);
    }
    public void updatePassword(@NotNull User user, String password,String oldPassword) {

        boolean isPasswordChanged=passwordEncoder.matches(oldPassword,user.getPassword());

        if(isPasswordChanged) {
            user.setPassword(passwordEncoder.encode(password));
            user.setActivationCode(UUID.randomUUID().toString());
            user.setActive(false);//
            userRepo.save(user);//
            sendMessage(user);//
        }
    }
//вынести в отдельный метод
    public void updateEmail(User user, String email) {
        boolean isEmailChanged=!email.equals(user.getEmail());

        if(isEmailChanged) {
            user.setEmail(email);
            user.setActivationCode(UUID.randomUUID().toString());
            user.setActive(false);
            userRepo.save(user);
            sendMessage(user);
        }
    }

    public void updateName(User user, String newName) {
        boolean isNameChanged=!newName.equals(user.getUsername());

        if(isNameChanged) {
            user.setUsername(newName);
            user.setActivationCode(UUID.randomUUID().toString());
            user.setActive(false);
            userRepo.save(user);
            sendMessage(user);
        }
    }
}
