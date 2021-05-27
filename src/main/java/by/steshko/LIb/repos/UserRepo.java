package com.asqint.webLib.repos;

import org.springframework.data.repository.CrudRepository;
import com.asqint.webLib.domain.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}