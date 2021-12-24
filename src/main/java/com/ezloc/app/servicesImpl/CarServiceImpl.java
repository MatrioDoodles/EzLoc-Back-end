package com.ezloc.app.servicesImpl;


import com.ezloc.app.entities.Car;
import com.ezloc.app.repositories.CarRepository;
import com.ezloc.app.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;


    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    @Transactional
    public Car add(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> findById(long id) {

        return carRepository.findById(id);
    }

    @Override
    @Transactional
    public Car update(Car car) {
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public String delete(long id) {
        carRepository.deleteById(id);
        return "Car N°"+id+" deleted successfully";
    }
}
