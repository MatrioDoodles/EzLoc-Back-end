package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Reservation;
import com.ezloc.app.services.ReservationService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {
    private ReservationService ReservationService;

    @Autowired
    public ReservationController(ReservationService ReservationService) {
        this.ReservationService = ReservationService;
    }

    @GetMapping
    public List<Reservation> findAll() {

        return ReservationService.findAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Reservation> Reservation = ReservationService.findById(id);

        if(Reservation.isPresent()) {
            Reservation resource = Reservation.get();

            return ResponseEntity.status(HttpStatus.OK).body(resource);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.RESERVATION_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Reservation> create(@RequestBody Optional<Reservation> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return ReservationService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Reservation> resource) {

        Optional<Reservation> Reservation = ReservationService.findById(id);
        if(Reservation.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(ReservationService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.RESERVATION_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Reservation> Reservation = ReservationService.findById(id);
        if(Reservation.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(ReservationService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.RESERVATION_NOT_FOUND);
    }
}
