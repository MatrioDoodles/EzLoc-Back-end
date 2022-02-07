package com.ezloc.app.controllers;


import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Car;
import com.ezloc.app.entities.Reservation;
import com.ezloc.app.services.CarService;
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
@RequestMapping(value = "/cars")
public class CarController {


    private final CarService carService;
    private final ReservationService reservationService;

    @Autowired
    public CarController(CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Car> findAll() {
        List<Car> allCars = carService.findAll();
     return allCars;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Car> car = carService.findById(id);

        if(car.isPresent()) {
            Car resource = car.get();

            return ResponseEntity.status(HttpStatus.OK).body(resource);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CAR_NOT_FOUND);
        }
    }
    @GetMapping(value = "/{id}/reservations")
    public ResponseEntity findByReservation(@PathVariable("id") Long id) {

        Optional<Car> car = carService.findById(id);

        if(car.isPresent()) {
            List<Reservation> reservationList = reservationService.findBycar_id(id);
            if(reservationList.size()>0){
                return ResponseEntity.status(HttpStatus.OK).body(reservationList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO_RESERVATIONS_FOR_THIS_CAR");
            }

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
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Optional<Car> resource) {

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
