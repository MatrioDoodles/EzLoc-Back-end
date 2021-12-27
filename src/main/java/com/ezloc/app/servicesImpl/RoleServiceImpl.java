package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Role;
import com.ezloc.app.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Optional<Role> add(Optional<Role> role) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findById(long id) {
        return Optional.empty();
    }

    @Override
    public String update(Long id, Optional<Role> role) {
        return null;
    }

    @Override
    public String delete(long id) {
        return null;
    }
}
