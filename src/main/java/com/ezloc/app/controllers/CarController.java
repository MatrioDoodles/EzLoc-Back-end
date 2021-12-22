package com.ezloc.app.controllers;


import com.ezloc.app.entities.Car;
import com.ezloc.app.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;
    @GetMapping
    public List<Car> findAll() {
        return carRepository.findAll();
    }
    @GetMapping(value = “/{id}”)
    public Car findById(@PathVariable(“id”) Long id) {
        return RestPreconditions.checkFound(carRepository.findById(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Car resource) {
        Preconditions.checkNotNull(resource);
        return carRepository.create(resource);
    }
    @PutMapping(value = “/{id}”)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable( “id” ) Long id, @RequestBody Car resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkNotNull(carRepository.getById(resource.getId()));
        carRepository.update(resource);
    }
    @DeleteMapping(value = “/{id}”)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(“id”) Long id) {
        carRepository.deleteById(id);
    }
}
