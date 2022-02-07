package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Role;
import com.ezloc.app.services.RoleService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/roles")
public class RoleController {
    private RoleService RoleService;

    @Autowired
    public RoleController(RoleService RoleService) {
        this.RoleService = RoleService;
    }

    @GetMapping
    public List<Role> findAll() {
        return RoleService.findAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        Optional<Role> Role = RoleService.findById(id);

        if(Role.isPresent()) {
            Role resource = Role.get();
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ROLE_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Role> create(@RequestBody Optional<Role> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return RoleService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Optional<Role> resource) {

        Optional<Role> Role = RoleService.findById(id);
        if(Role.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(RoleService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ROLE_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Role> Role = RoleService.findById(id);
        if(Role.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(RoleService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ROLE_NOT_FOUND);
    }
}
