package com.ezloc.app.services;

import com.ezloc.app.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> add(Optional<User> user);
    Optional<User>  findById(long id);
    User update(Long id,Optional<User> user);
    User updatePassword(Long id,String password);
    String delete(long id);
    User findByusername(String username);
    List<User> findByenterprise_id(Long id);
    List<User> findByenterprise_idAndrole_id(Long enterprise_id,Long role_id);
}
