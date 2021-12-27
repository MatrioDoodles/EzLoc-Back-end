package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.User;
import com.ezloc.app.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> add(Optional<User> user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public String update(Long id, Optional<User> user) {
        return null;
    }

    @Override
    public String delete(long id) {
        return null;
    }
}
