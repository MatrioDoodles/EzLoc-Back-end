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
            if(Reservation.getClient()!=null)
            {Link clientLink = linkTo(EnterpriseController.class).slash(Reservation.getClient().getId()).withRel("Client");
                Reservation.add(clientLink);}
            if(Reservation.getCar()!=null)
            {Link carLink = linkTo(EnterpriseController.class).slash(Reservation.getCar().getId()).withRel("Car");
                Reservation.add(carLink);}
            if(Reservation.getContract()!=null)
            {Link contractLink = linkTo(EnterpriseController.class).slash(Reservation.getContract().getId()).withRel("Contract");
                Reservation.add(contractLink);}
            if(Reservation.getInvoice()!=null)
            {Link invoiceLink = linkTo(EnterpriseController.class).slash(Reservation.getInvoice().getId()).withRel("Invoice");
                Reservation.add(invoiceLink);}
            if(Reservation.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(Reservation.getEnterprise().getId()).withRel("Enterprise");
                Reservation.add(enterpriseLink);}
            if(Reservation.getUser()!=null)
            {Link userLink = linkTo(EnterpriseController.class).slash(Reservation.getUser().getId()).withRel("User");
                Reservation.add(userLink);}
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
            if(resource.getClient()!=null)
            {Link clientLink = linkTo(EnterpriseController.class).slash(resource.getClient().getId()).withRel("Client");
                resource.add(clientLink);}
            if(resource.getCar()!=null)
            {Link carLink = linkTo(EnterpriseController.class).slash(resource.getCar().getId()).withRel("Car");
                resource.add(carLink);}
            if(resource.getContract()!=null)
            {Link contractLink = linkTo(EnterpriseController.class).slash(resource.getContract().getId()).withRel("Contract");
                resource.add(contractLink);}
            if(resource.getInvoice()!=null)
            {Link invoiceLink = linkTo(EnterpriseController.class).slash(resource.getInvoice().getId()).withRel("Invoice");
                resource.add(invoiceLink);}
            if(resource.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(resource.getEnterprise().getId()).withRel("Enterprise");
                resource.add(enterpriseLink);}
            if(resource.getUser()!=null)
            {Link userLink = linkTo(EnterpriseController.class).slash(resource.getUser().getId()).withRel("User");
                resource.add(userLink);}
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
