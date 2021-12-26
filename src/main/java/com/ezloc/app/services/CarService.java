package com.ezloc.app.services;


import com.ezloc.app.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll();
    Optional<Car> add(Optional<Car> car);
    Optional<Car>  findById(long id);
    String update(Long id,Optional<Car> car);
    String delete(long id);
    }
