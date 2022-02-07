package com.ezloc.app.controllers;


import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Agency;
import com.ezloc.app.services.AgencyService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/agencies")
public class AgencyController {

    private final AgencyService agencyService;

    @Autowired
    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping
    public List<Agency> findAll() {
        List<Agency> allAgencys = agencyService.findAll();
        return allAgencys;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Agency> Agency = agencyService.findById(id);

        if(Agency.isPresent()) {
            Agency resource = Agency.get();

            return ResponseEntity.status(HttpStatus.OK).body(resource);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.AGENCY_NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Agency> create(@RequestBody Optional<Agency> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return agencyService.add(resource);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Optional<Agency> resource) {

        Optional<Agency> Agency = agencyService.findById(id);
        if(Agency.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(agencyService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.AGENCY_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Agency> Agency = agencyService.findById(id);
        if(Agency.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(agencyService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.AGENCY_NOT_FOUND);
    }
}
