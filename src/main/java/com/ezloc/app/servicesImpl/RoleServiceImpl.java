package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Role;
import com.ezloc.app.repositories.RoleRepository;
import com.ezloc.app.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Role> add(Optional<Role> role) {
        Optional<Role> resource = Optional.of(roleRepository.save(role.get()));
        return resource;
    }

    @Override
    public Optional<Role> findById(long id) {
        return roleRepository.findById(id);
    }

    @Override
    @Transactional
    public String update(Long id, Optional<Role> role) {
        Role resource = roleRepository.getById(id);
        resource.setLabel(Optional.ofNullable(role).map(c->c.get().getLabel()).orElse(resource.getLabel()));
        return "Role N° "+id+" Updated Successfully";
    }

    @Override
    @Transactional
    public String delete(long id) {
        return "Role N° "+id+" Deleted Successfully";
    }
}
