package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Maintenance;
import com.ezloc.app.services.MaintenanceService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/maintenances")
public class MaintenanceController {
    private MaintenanceService MaintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService MaintenanceService) {
        this.MaintenanceService = MaintenanceService;
    }

    @GetMapping
    public List<Maintenance> findAll() {

        return MaintenanceService.findAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Maintenance> Maintenance = MaintenanceService.findById(id);

        if(Maintenance.isPresent()) {
            Maintenance resource = Maintenance.get();
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MAINTENANCE_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Maintenance> create(@RequestBody Optional<Maintenance> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return MaintenanceService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Optional<Maintenance> resource) {

        Optional<Maintenance> Maintenance = MaintenanceService.findById(id);
        if(Maintenance.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(MaintenanceService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MAINTENANCE_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Maintenance> Maintenance = MaintenanceService.findById(id);
        if(Maintenance.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(MaintenanceService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.MAINTENANCE_NOT_FOUND);
    }
}
