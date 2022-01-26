package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Settings;
import com.ezloc.app.services.SettingsService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/settings")
public class SettingsController {
    private SettingsService SettingsService;

    @Autowired
    public SettingsController(SettingsService SettingsService) {
        this.SettingsService = SettingsService;
    }

    @GetMapping
    public List<Settings> findAll() {
        return SettingsService.findAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Settings> Settings = SettingsService.findById(id);

        if(Settings.isPresent()) {
            Settings resource = Settings.get();
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.SETTINGS_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Settings> create(@RequestBody Optional<Settings> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return SettingsService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Settings> resource) {

        Optional<Settings> Settings = SettingsService.findById(id);
        if(Settings.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(SettingsService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.SETTINGS_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Settings> Settings = SettingsService.findById(id);
        if(Settings.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(SettingsService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.SETTINGS_NOT_FOUND);
    }
}
