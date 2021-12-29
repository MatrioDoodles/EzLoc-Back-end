package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.User;
import com.ezloc.app.services.UserService;
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
@RequestMapping(value = "/users")
public class UserController {
    private UserService UserService;

    @Autowired
    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping
    public CollectionModel<User> findAll() {
        List<User> allUsers = UserService.findAll();
        for (User User : allUsers) {
            Long UserId = User.getId();
            Link selfLink = linkTo(UserController.class).slash(UserId).withSelfRel();
            User.add(selfLink);
            if(User.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(User.getEnterprise().getId()).withRel("Enterprise");
                User.add(enterpriseLink);}
            if(User.getRole()!=null)
            {Link roleLink = linkTo(EnterpriseController.class).slash(User.getRole().getId()).withRel("Role");
                User.add(roleLink);}
        }


        Link link = linkTo(UserController.class).withSelfRel();
        CollectionModel<User> result = CollectionModel.of(allUsers, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<User> User = UserService.findById(id);

        if(User.isPresent()) {
            User resource = User.get();
            Link selfLink = linkTo(UserController.class).slash(id).withSelfRel();
            EntityModel<User> result = EntityModel.of(resource,selfLink);
            if(resource.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(resource.getEnterprise().getId()).withRel("Enterprise");
                result.add(enterpriseLink);}
            if(resource.getRole()!=null)
            {Link roleLink = linkTo(EnterpriseController.class).slash(resource.getRole().getId()).withRel("Role");
                resource.add(roleLink);}
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.USER_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<User> create(@RequestBody Optional<User> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return UserService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<User> resource) {

        Optional<User> User = UserService.findById(id);
        if(User.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(UserService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.USER_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<User> User = UserService.findById(id);
        if(User.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(UserService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.USER_NOT_FOUND);
    }
}
