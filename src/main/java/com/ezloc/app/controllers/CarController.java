package com.ezloc.app.controllers;


import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Car;
import com.ezloc.app.services.CarService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

    @Autowired
    private CarService carService;



    @GetMapping
    public List<Car> findAll() {
        return carService.findAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Optional<Car> car = carService.findById(id);
        if(car.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(car);
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
