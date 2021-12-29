package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.History;
import com.ezloc.app.entities.Reservation;
import com.ezloc.app.entities.User;
import com.ezloc.app.services.HistoryService;
import com.ezloc.app.services.ReservationService;
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

import static com.ezloc.app.config.Constants.ENTERPRISE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final ReservationService reservationService;
    private final HistoryService historyService;

    @Autowired
    public UserController(UserService userService,
                          ReservationService reservationService,
                          HistoryService historyService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.historyService = historyService;
    }

    @GetMapping
    public CollectionModel<User> findAll() {
        List<User> allUsers = userService.findAll();
        for (User user : allUsers) {
            Long userId = user.getId();
            Link selfLink = linkTo(UserController.class).slash(userId).withSelfRel();
            user.add(selfLink);
            if(user.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(user.getEnterprise().getId()).withRel(ENTERPRISE);
                user.add(enterpriseLink);}
            if(user.getRole()!=null)
            {Link roleLink = linkTo(EnterpriseController.class).slash(user.getRole().getId()).withRel("Role");
                user.add(roleLink);}
        }


        Link link = linkTo(UserController.class).withSelfRel();
        return CollectionModel.of(allUsers, link);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        Optional<User> user = userService.findById(id);

        if(user.isPresent()) {
            User resource = user.get();
            Link selfLink = linkTo(UserController.class).slash(id).withSelfRel();
            EntityModel<User> result = EntityModel.of(resource,selfLink);
            if(resource.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(resource.getEnterprise().getId()).withRel(ENTERPRISE);
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

    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> findByUsername(@PathVariable("username") String username) {

        Optional<User> user = Optional.of(userService.findByusername(username));

        if(user.isPresent()==true) {
            User resource = user.get();
            Link selfLink = linkTo(UserController.class).slash(username).withSelfRel();
            EntityModel<User> result = EntityModel.of(resource,selfLink);
            if(resource.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(resource.getEnterprise().getId()).withRel(ENTERPRISE);
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
    @GetMapping(value = "/{id}/reservations")
    public ResponseEntity<Object> findReservationsByUser(@PathVariable("id") Long id) {

        Optional<User> user = userService.findById(id);

        if(user.isPresent()) {
            List<Reservation> reservationList = reservationService.findBycar_id(id);
            if(!reservationList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(reservationList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO_RESERVATIONS_FOR_THIS_USER");
            }

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.USER_NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}/history")
    public ResponseEntity<Object> findHistoryByUser(@PathVariable("id") Long id) {

        Optional<User> user = userService.findById(id);

        if(user.isPresent()) {
            List<History> historyList = historyService.findByuser_id(id);
            if(!historyList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(historyList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO_HISTORY_FOR_THIS_USER");
            }

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.USER_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<User> create(@RequestBody Optional<User> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return userService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<User> resource) {

        Optional<User> user = userService.findById(id);
        if(user.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.USER_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.USER_NOT_FOUND);
    }
}
