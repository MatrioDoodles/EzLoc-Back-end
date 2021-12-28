package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Role;
import com.ezloc.app.services.RoleService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {
    private RoleService RoleService;

    @Autowired
    public RoleController(RoleService RoleService) {
        this.RoleService = RoleService;
    }

    @GetMapping
    public CollectionModel<Role> findAll() {
        List<Role> allRoles = RoleService.findAll();
        for (Role Role : allRoles) {
            Long RoleId = Role.getId();
            Link selfLink = linkTo(RoleController.class).slash(RoleId).withSelfRel();
            Role.add(selfLink);
        }


        Link link = linkTo(RoleController.class).withSelfRel();
        CollectionModel<Role> result = CollectionModel.of(allRoles, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Role> Role = RoleService.findById(id);

        if(Role.isPresent()) {
            Role resource = Role.get();
            Link selfLink = linkTo(RoleController.class).slash(id).withSelfRel();
            EntityModel<Role> result = EntityModel.of(resource,selfLink);
            return ResponseEntity.status(HttpStatus.OK).body(result);
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
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Role> resource) {

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
