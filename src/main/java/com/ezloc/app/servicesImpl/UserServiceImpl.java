package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.User;
import com.ezloc.app.repositories.UserRepository;
import com.ezloc.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> add(Optional<User> user) {
        System.out.println(user.get().toString());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User resource = user.get();
        String endodedpassword = encoder.encode(resource.getPassword());
        resource.setPassword(endodedpassword);
        return Optional.of(userRepository.save(resource));
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User update(Long id, Optional<User> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User resource = userRepository.getById(id);
        resource.setName(Optional.ofNullable(user).map(c->c.get().getName()).orElse(resource.getName()));
        resource.setSurname(Optional.ofNullable(user).map(c->c.get().getSurname()).orElse(resource.getSurname()));
        resource.setMail(Optional.ofNullable(user).map(c->c.get().getMail()).orElse(resource.getMail()));
        resource.setPhone(Optional.ofNullable(user).map(c->c.get().getPhone()).orElse(resource.getPhone()));
        resource.setAdress(Optional.ofNullable(user).map(c->c.get().getAdress()).orElse(resource.getAdress()));
        resource.setUsername(Optional.ofNullable(user).map(c->c.get().getUsername()).orElse(resource.getUsername()));
        resource.setPassword(Optional.ofNullable(user).map(c -> encoder.encode(c.get().getPassword())).orElse(resource.getPassword()));
        resource.setCity(Optional.ofNullable(user).map(c->c.get().getCity()).orElse(resource.getCity()));
        resource.setActivated(Optional.ofNullable(user).map(c->c.get().isActivated()).orElse(resource.isActivated()));
        resource.setRole(Optional.ofNullable(user).map(c->c.get().getRole()).orElse(resource.getRole()));
        resource.setEnterprise(Optional.ofNullable(user).map(c->c.get().getEnterprise()).orElse(resource.getEnterprise()));


        return userRepository.save(resource);
    }

    @Override
    public User updatePassword(Long id, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User resource = userRepository.getById(id);
        resource.setPassword(encoder.encode(password));
        return userRepository.save(resource);
    }

    @Override
    @Transactional
    public String delete(long id) {
        userRepository.deleteById(id);
        return "User N° "+id+" Deleted Successfully";
    }

    @Override
    public User findByusername(String username) {
        return userRepository.findByusername(username);
    }

    @Override
    public List<User> findByenterprise_id(Long id) {
        return userRepository.findByEnterprise_id(id);
    }

    @Override
    public List<User> findByenterprise_idAndrole_id(Long enterprise_id, Long role_id) {
        return userRepository.findByEnterprise_idAndRole_id(enterprise_id,role_id);
    }
}
