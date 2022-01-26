package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.*;
import com.ezloc.app.services.*;
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

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/enterprises")
public class EnterpriseController {
    private final EnterpriseService enterpriseService;
    private final ReservationService reservationService;
    private final CarService carService;
    private final ClientService clientService;
    private final MetricService metricService;
    private final UserService userService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService,
                                ReservationService reservationService,
                                CarService carService,
                                ClientService clientService,
                                MetricService metricService,
                                UserService userService) {
        this.enterpriseService = enterpriseService;
        this.reservationService = reservationService;
        this.carService = carService;
        this.clientService = clientService;
        this.metricService = metricService;
        this.userService = userService;
    }

    @GetMapping
    public CollectionModel<Enterprise> findAll() {
        List<Enterprise> allEnterprises = enterpriseService.findAll();
        for (Enterprise enterprise : allEnterprises) {
            Long enterpriseId = enterprise.getId();
            Link selfLink = linkTo(EnterpriseController.class).slash(enterpriseId).withSelfRel();
            enterprise.add(selfLink);
        }


        Link link = linkTo(EnterpriseController.class).withSelfRel();
        return CollectionModel.of(allEnterprises, link);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        Optional<Enterprise> enterprise = enterpriseService.findById(id);

        if(enterprise.isPresent()) {
            Enterprise resource = enterprise.get();
            Link selfLink = linkTo(EnterpriseController.class).slash(id).withSelfRel();
            EntityModel<Enterprise> result = EntityModel.of(resource,selfLink);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}/cars")
    public ResponseEntity<Object> findCarsByEnterprise(@PathVariable("id") Long id) {

        Optional<Enterprise> enterprise = enterpriseService.findById(id);

        if(enterprise.isPresent()) {
            List<Car> carList = carService.findByenterprise_id(id);
            if(!carList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(carList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO_CARS_FOR_THIS_ENTERPRISE");
            }

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}/clients")
    public ResponseEntity<Object> findClientsByEnterprise(@PathVariable("id") Long id) {

        Optional<Enterprise> enterprise = enterpriseService.findById(id);

        if(enterprise.isPresent()) {
            List<Client> clientList = clientService.findByenterprise_id(id);
            if(!clientList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(clientList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO_CLIENTS_FOR_THIS_ENTERPRISE");
            }

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}/metrics")
    public ResponseEntity<Object> findMetricsByEnterprise(@PathVariable("id") Long id) {

        Optional<Enterprise> enterprise = enterpriseService.findById(id);

        if(enterprise.isPresent()) {
            List<Metric> metricList = metricService.findByenterprise_id(id);
            if(!metricList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(metricList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO_METRICS_FOR_THIS_ENTERPRISE");
            }

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}/users")
    public ResponseEntity<Object> findUsersByEnterprise(@PathVariable("id") Long id) {

        Optional<Enterprise> enterprise = enterpriseService.findById(id);

        if(enterprise.isPresent()) {
            List<User> userList = userService.findByenterprise_id(id);
            if(!userList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(userList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO_USERS_FOR_THIS_ENTERPRISE");
            }

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}/reservations")
    public ResponseEntity<Object> findReservationsByEnterprise(@PathVariable("id") Long id) {

        Optional<Enterprise> enterprise = enterpriseService.findById(id);

        if(enterprise.isPresent()) {
            List<Reservation> reservationList = reservationService.findBycar_id(id);
            if(!reservationList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(reservationList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO_RESERVATIONS_FOR_THIS_ENTERPRISE");
            }

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Enterprise> create(@RequestBody Optional<Enterprise> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return enterpriseService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Enterprise> resource) {

        Optional<Enterprise> enterprise = enterpriseService.findById(id);
        if(enterprise.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(enterpriseService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Enterprise> enterprise = enterpriseService.findById(id);
        if(enterprise.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(enterpriseService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
    }
}
