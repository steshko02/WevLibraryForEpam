package by.steshko.LIb.api;

import by.steshko.LIb.domain.User;
import com.sun.istack.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService extends UserDetailsService {
     boolean addUser(User user);

     void sendMessage(User user);
     List<User> getUsers();
      @Override
     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException ;

     boolean activateUser(String code);

     boolean ifExistByEmail(String email);

     boolean ifExistByName(String name);

     void updatePassword(@NotNull User user, String password, String oldPassword);

     void updateEmail(User user, String email);

     void updateName(User user, String newName) ;

     User findById(Long userId) ;

     void save(User user) ;

     Iterable<User> getAll();
     void blockUsers(Long id);
}
