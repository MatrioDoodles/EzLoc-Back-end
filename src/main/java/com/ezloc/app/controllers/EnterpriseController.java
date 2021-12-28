package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Enterprise;
import com.ezloc.app.services.EnterpriseService;
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

@RestController
@RequestMapping(value = "/enterprises")
public class EnterpriseController {
    private EnterpriseService EnterpriseService;

    @Autowired
    public EnterpriseController(EnterpriseService EnterpriseService) {
        this.EnterpriseService = EnterpriseService;
    }

    @GetMapping
    public CollectionModel<Enterprise> findAll() {
        List<Enterprise> allEnterprises = EnterpriseService.findAll();
        for (Enterprise Enterprise : allEnterprises) {
            Long EnterpriseId = Enterprise.getId();
            Link selfLink = linkTo(EnterpriseController.class).slash(EnterpriseId).withSelfRel();
            Enterprise.add(selfLink);
        }


        Link link = linkTo(EnterpriseController.class).withSelfRel();
        CollectionModel<Enterprise> result = CollectionModel.of(allEnterprises, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Enterprise> Enterprise = EnterpriseService.findById(id);

        if(Enterprise.isPresent()) {
            Enterprise resource = Enterprise.get();
            Link selfLink = linkTo(EnterpriseController.class).slash(id).withSelfRel();
            EntityModel<Enterprise> result = EntityModel.of(resource,selfLink);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Enterprise> create(@RequestBody Optional<Enterprise> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return EnterpriseService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Enterprise> resource) {

        Optional<Enterprise> Enterprise = EnterpriseService.findById(id);
        if(Enterprise.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(EnterpriseService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Enterprise> Enterprise = EnterpriseService.findById(id);
        if(Enterprise.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(EnterpriseService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.ENTERPRISE_NOT_FOUND);
    }
}
