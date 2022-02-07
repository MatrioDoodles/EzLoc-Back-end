package com.ezloc.app.services;

import com.ezloc.app.entities.Maintenance;

import java.util.List;
import java.util.Optional;

public interface MaintenanceService {
    List<Maintenance> findAll();
    Optional<Maintenance> add(Optional<Maintenance> maintenance);
    Optional<Maintenance>  findById(long id);
    Maintenance update(Long id,Optional<Maintenance> maintenance);
    String delete(long id);
}
