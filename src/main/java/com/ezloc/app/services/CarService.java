package com.ezloc.app.services;


import com.ezloc.app.entities.Car;
import com.ezloc.app.entities.ConstructorName;
import com.ezloc.app.entities.Model;
import com.ezloc.app.entities.Trim;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll();
    Optional<Car> add(Optional<Car> car);
    Optional<Car>  findById(long id);
    Car update(Long id,Optional<Car> car);
    String delete(long id);
    List<Car> findByenterprise_id(Long id);
    List<ConstructorName> findAllConstructors();
    List<Model> findModelsByConstructor(Long id);
    List<Trim> findTrimsByConstructor(Long id);
    }
