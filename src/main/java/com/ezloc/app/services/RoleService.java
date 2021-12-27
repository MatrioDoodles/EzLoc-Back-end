package com.ezloc.app.services;

import com.ezloc.app.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> add(Optional<Role> role);
    Optional<Role>  findById(long id);
    String update(Long id,Optional<Role> role);
    String delete(long id);
}
