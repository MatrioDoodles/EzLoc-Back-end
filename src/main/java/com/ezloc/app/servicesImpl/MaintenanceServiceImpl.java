package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Maintenance;
import com.ezloc.app.services.MaintenanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Override
    public List<Maintenance> findAll() {
        return null;
    }

    @Override
    public Optional<Maintenance> add(Optional<Maintenance> maintenance) {
        return Optional.empty();
    }

    @Override
    public Optional<Maintenance> findById(long id) {
        return Optional.empty();
    }

    @Override
    public String update(Long id, Optional<Maintenance> maintenance) {
        return null;
    }

    @Override
    public String delete(long id) {
        return null;
    }
}
