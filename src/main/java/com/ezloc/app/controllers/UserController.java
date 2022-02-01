package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.History;
import com.ezloc.app.entities.Reservation;
import com.ezloc.app.entities.User;
import com.ezloc.app.services.HistoryService;
import com.ezloc.app.services.ReservationService;
import com.ezloc.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(value = "*")
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

    @GetMapping(value="/all")
    public List<User> findAll() {
        List<User> allUsers = userService.findAll();

        return allUsers;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        Optional<User> user = userService.findById(id);

        if(user.isPresent()) {
            User resource = user.get();
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.USER_NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findByUsername(@RequestParam(value="username") String username) {

        Optional<User> user = Optional.of(userService.findByusername(username));

        if(user.isPresent()==true) {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.USER_NOT_FOUND);
        }
    }
    @GetMapping(value="passwordChange")
    public ResponseEntity<Object> passwordChange(@RequestParam(value="id") Long id ,@RequestParam(value="pass") String password) {

        Optional<User> user = userService.findById(id);

        if(user.isPresent()==true) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updatePassword(id,password));
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

        return userService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Optional<User> resource) {

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
