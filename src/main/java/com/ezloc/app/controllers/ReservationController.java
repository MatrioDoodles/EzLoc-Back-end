package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Reservation;
import com.ezloc.app.services.ReservationService;
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
@RequestMapping(value = "/reservations")
public class ReservationController {
    private ReservationService ReservationService;

    @Autowired
    public ReservationController(ReservationService ReservationService) {
        this.ReservationService = ReservationService;
    }

    @GetMapping
    public CollectionModel<Reservation> findAll() {
        List<Reservation> allReservations = ReservationService.findAll();
        for (Reservation Reservation : allReservations) {
            Long ReservationId = Reservation.getId();
            Link selfLink = linkTo(ReservationController.class).slash(ReservationId).withSelfRel();
            Reservation.add(selfLink);
        }


        Link link = linkTo(ReservationController.class).withSelfRel();
        CollectionModel<Reservation> result = CollectionModel.of(allReservations, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Reservation> Reservation = ReservationService.findById(id);

        if(Reservation.isPresent()) {
            Reservation resource = Reservation.get();
            Link selfLink = linkTo(ReservationController.class).slash(id).withSelfRel();
            EntityModel<Reservation> result = EntityModel.of(resource,selfLink);
            return ResponseEntity.status(HttpStatus.OK).body(result);
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
