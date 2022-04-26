package com.ezloc.app.servicesImpl;


import com.ezloc.app.entities.Car;
import com.ezloc.app.entities.ConstructorName;
import com.ezloc.app.entities.Model;
import com.ezloc.app.entities.Trim;
import com.ezloc.app.repositories.CarRepository;
import com.ezloc.app.repositories.ConstructorNameRepository;
import com.ezloc.app.repositories.ModelRepository;
import com.ezloc.app.repositories.TrimRepository;
import com.ezloc.app.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {


    private final CarRepository carRepository;
    private final TrimRepository trimRepository;
    private final ConstructorNameRepository constructorNameRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, TrimRepository trimRepository, ConstructorNameRepository constructorNameRepository, ModelRepository modelRepository) {
        this.carRepository = carRepository;
        this.trimRepository = trimRepository;
        this.constructorNameRepository = constructorNameRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Car> add(Optional<Car> car) {
        return Optional.of(carRepository.save(car.get()));
    }

    @Override
    public Optional<Car> findById(long id) {

        return carRepository.findById(id);
    }

    @Override
    @Transactional
    public Car update(Long id,Optional<Car> car) {


            Car resource = carRepository.getById(id);
            resource.setConstructorName(Optional.ofNullable(car).map(c->c.get().getConstructorName()).orElse(resource.getConstructorName()));
            resource.setModel(Optional.ofNullable(car).map(c->c.get().getModel()).orElse(resource.getModel()));
            resource.setColor(Optional.ofNullable(car).map(c->c.get().getColor()).orElse(resource.getColor()));
            resource.setYear(Optional.ofNullable(car).map(c->c.get().getYear()).orElse(resource.getYear()));
            resource.setCategory(Optional.ofNullable(car).map(c->c.get().getCategory()).orElse(resource.getCategory()));
            resource.setTrim(Optional.ofNullable(car).map(c->c.get().getTrim()).orElse(resource.getTrim()));
            resource.setFuel(Optional.ofNullable(car).map(c->c.get().getFuel()).orElse(resource.getFuel()));
            resource.setMileage(Optional.ofNullable(car).map(c->c.get().getMileage()).orElse(resource.getMileage()));
            resource.setGearbox(Optional.ofNullable(car).map(c->c.get().getGearbox()).orElse(resource.getGearbox()));
            resource.setRegistration(Optional.ofNullable(car).map(c->c.get().getRegistration()).orElse(resource.getRegistration()));
            resource.setAvailable(Optional.ofNullable(car).map(c->c.get().isAvailable()).orElse(resource.isAvailable()));
            resource.setFiscalPower(Optional.ofNullable(car).map(c->c.get().getFiscalPower()).orElse(resource.getFiscalPower()));



        return carRepository.save(resource);

    }

    @Override
    @Transactional
    public String delete(long id) {
        carRepository.deleteById(id);
        return "Car N°" +id+ " deleted successfully";
    }

    @Override
    public List<Car> findByenterprise_id(Long id) {
        return carRepository.findByenterprise_id(id);
    }

    @Override
    public List<ConstructorName> findAllConstructors() {
        return constructorNameRepository.findAll();
    }

    @Override
    public List<Model> findModelsByConstructor(Long id) {
        return modelRepository.findByconstructorName_id(id);
    }

    @Override
    public List<Trim> findTrimsByConstructor(Long id) {
        return trimRepository.findByconstructorName_id(id);
    }
}
