package com.ezloc.app.services;

import com.ezloc.app.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> add(Optional<User> user);
    Optional<User>  findById(long id);
    String update(Long id,Optional<User> user);
    String delete(long id);
    User findByusername(String username);
    List<User> findByenterprise_id(Long id);
}
