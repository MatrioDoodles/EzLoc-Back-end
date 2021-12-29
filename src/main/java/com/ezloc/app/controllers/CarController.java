package com.ezloc.app.controllers;


import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Car;
import com.ezloc.app.entities.Maintenance;
import com.ezloc.app.services.CarService;
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
@RequestMapping(value = "/cars")
public class CarController {


    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public CollectionModel<Car> findAll() {
        List<Car> allCars = carService.findAll();
        for (Car car : allCars) {
            Long carId = car.getId();
            Link selfLink = linkTo(CarController.class).slash(carId).withSelfRel();
            car.add(selfLink);
            if(car.getMaintenance()!=null)
            {Link maintenanceLink = linkTo(MaintenanceController.class).slash(car.getMaintenance().getId()).withRel("Maintenance");
            car.add(maintenanceLink);}
            if(car.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(car.getEnterprise().getId()).withRel("Enterprise");
                car.add(enterpriseLink);}

            }


        Link link = linkTo(CarController.class).withSelfRel();
        CollectionModel<Car> result = CollectionModel.of(allCars, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Car> car = carService.findById(id);

        if(car.isPresent()) {
            Car resource = car.get();
            Link selfLink = linkTo(CarController.class).slash(id).withSelfRel();
            EntityModel<Car> result = EntityModel.of(resource,selfLink);
            if(resource.getMaintenance()!=null) {
                Link maintenanceLink = linkTo(MaintenanceController.class).slash(resource.getMaintenance().getId()).withRel("Maintenance");
                result.add(maintenanceLink);
            }
            if(resource.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(resource.getEnterprise().getId()).withRel("Enterprise");
                result.add(enterpriseLink);}
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CAR_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Car> create(@RequestBody Optional<Car> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return carService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Car> resource) {

        Optional<Car> car = carService.findById(id);
        if(car.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(carService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CAR_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Car> car = carService.findById(id);
        if(car.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(carService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CAR_NOT_FOUND);
    }
}
