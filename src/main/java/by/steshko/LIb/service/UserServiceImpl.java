package by.steshko.LIb.service;

import by.steshko.LIb.api.UserService;
import by.steshko.LIb.domain.Role;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.UserRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private MailSenderImpl mailSenderImpl;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public boolean addUser(User user){
        if(!userRepo.existsByEmail(user.getEmail()) &&
                !userRepo.existsByUsername(user.getUsername())){
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        sendMessage(user);
        return true;
        }
        return false;
    }

    public  void sendMessage(User user){
        String message = String.format(
                "Hello,%s\n"+
                        "Welcome to WebLib!\n"+
                        "To activate your account visit this link: http://localhost:8080/activate/%s",
                user.getUsername(),user.getActivationCode()
        );
        mailSenderImpl.send(user.getEmail(), "Activation code", message);
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
            user.setActive(false);
            userRepo.save(user);
            sendMessage(user);
        }
    }

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

    public User findById(Long userId) {
      return  userRepo.findById(userId).orElse(null);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public Iterable<User> getAll() {
        return userRepo.findAll();
    }

    public void blockUsers(Long id) {
        User user = findById(id);
        if(user.isActive()) {
            user.setActive(false);
        }
        else {
            user.setActive(true);
        }
        userRepo.save(user);
    }

    public List<User> getUsers(){
        List<User> allUsers = (List<User>) userRepo.findAll();

        List<User> usersByUserRole= new ArrayList<>();
        allUsers.stream().filter(u->!u.getRoles().
                contains(Role.ADMIN)).forEach(usersByUserRole::add);


        return usersByUserRole;
    }
}
