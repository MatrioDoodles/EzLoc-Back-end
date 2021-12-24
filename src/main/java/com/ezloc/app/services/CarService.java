package com.ezloc.app.services;


import com.ezloc.app.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll();
    Car add(Car car);
    Optional<Car>  findById(long id);
    Car update(Car car);
    String delete(long id);
    }
