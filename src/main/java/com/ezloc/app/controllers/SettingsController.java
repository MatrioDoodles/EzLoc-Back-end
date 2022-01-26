package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Settings;
import com.ezloc.app.services.SettingsService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
    public CollectionModel<Settings> findAll() {
        List<Settings> allSettingss = SettingsService.findAll();
        for (Settings Settings : allSettingss) {
            Long SettingsId = Settings.getId();
            Link selfLink = linkTo(SettingsController.class).slash(SettingsId).withSelfRel();
            Settings.add(selfLink);
            if(Settings.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(Settings.getEnterprise().getId()).withRel("Enterprise");
                Settings.add(enterpriseLink);}
        }


        Link link = linkTo(SettingsController.class).withSelfRel();
        CollectionModel<Settings> result = CollectionModel.of(allSettingss, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Settings> Settings = SettingsService.findById(id);

        if(Settings.isPresent()) {
            Settings resource = Settings.get();
            Link selfLink = linkTo(SettingsController.class).slash(id).withSelfRel();
            EntityModel<Settings> result = EntityModel.of(resource,selfLink);
            if(resource.getEnterprise()!=null)
            {Link enterpriseLink = linkTo(EnterpriseController.class).slash(resource.getEnterprise().getId()).withRel("Enterprise");
                result.add(enterpriseLink);}
            return ResponseEntity.status(HttpStatus.OK).body(result);
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
