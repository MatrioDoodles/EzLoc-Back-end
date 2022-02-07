package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Maintenance;
import com.ezloc.app.repositories.MaintenanceRepository;
import com.ezloc.app.services.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {


    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Maintenance> add(Optional<Maintenance> maintenance) {
        return Optional.of(maintenanceRepository.save(maintenance.get()));
    }

    @Override
    public Optional<Maintenance> findById(long id) {
        return maintenanceRepository.findById(id);
    }

    @Override
    @Transactional
    public Maintenance update(Long id, Optional<Maintenance> maintenance) {
        Maintenance resource = maintenanceRepository.getById(id);
        resource.setAssurance(Optional.ofNullable(maintenance).map(c->c.get().getAssurance()).orElse(resource.getAssurance()));
        resource.setAssuranceEndDate(Optional.ofNullable(maintenance).map(c->c.get().getAssuranceEndDate()).orElse(resource.getAssuranceEndDate()));
        resource.setAssurancePaid(Optional.ofNullable(maintenance).map(c->c.get().isAssurancePaid()).orElse(resource.isAssurancePaid()));
        resource.setLastMileage(Optional.ofNullable(maintenance).map(c->c.get().getLastMileage()).orElse(resource.getLastMileage()));
        resource.setOilChangeKm(Optional.ofNullable(maintenance).map(c->c.get().getOilChangeKm()).orElse(resource.getOilChangeKm()));
        resource.setTechnicalVisit(Optional.ofNullable(maintenance).map(c->c.get().getTechnicalVisit()).orElse(resource.getTechnicalVisit()));
        resource.setVignette(Optional.ofNullable(maintenance).map(c->c.get().getVignette()).orElse(resource.getVignette()));
        resource.setVignettePaid(Optional.ofNullable(maintenance).map(c->c.get().isVignettePaid()).orElse(resource.isVignettePaid()));
        return  maintenanceRepository.save(resource);
    }

    @Override
    @Transactional
    public String delete(long id) {
        maintenanceRepository.deleteById(id);
        return "Maintenance N° "+id+" Deleted Successfully";
    }
}
