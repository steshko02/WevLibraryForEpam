package by.steshko.LIb.repos;

import by.steshko.LIb.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByActivationCode(String code);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}