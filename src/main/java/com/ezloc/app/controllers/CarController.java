package com.ezloc.app.controllers;


import com.ezloc.app.config.RestPreconditions;
import com.ezloc.app.entities.Car;
import com.ezloc.app.repositories.CarRepository;
import com.ezloc.app.services.CarService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Optional<Car> findById(@PathVariable("id") Long id) {

        return RestPreconditions.checkNotNull(carService.findById(id),"Car Not Found");//carService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car create(@RequestBody Car resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return carService.add(resource);
    }
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Car resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkNotNull(carService.findById(resource.getId()),"Car Not Found");
        carService.update(resource);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        carService.delete(id);
    }
}
