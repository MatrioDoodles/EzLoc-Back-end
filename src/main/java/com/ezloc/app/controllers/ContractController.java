package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Contract;
import com.ezloc.app.services.ContractService;
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
@RequestMapping(value = "/contracts")
public class ContractController {
    private ContractService ContractService;

    @Autowired
    public ContractController(ContractService ContractService) {
        this.ContractService = ContractService;
    }

    @GetMapping
    public CollectionModel<Contract> findAll() {
        List<Contract> allContracts = ContractService.findAll();
        for (Contract Contract : allContracts) {
            Long ContractId = Contract.getId();
            Link selfLink = linkTo(ContractController.class).slash(ContractId).withSelfRel();
            Contract.add(selfLink);
        }


        Link link = linkTo(ContractController.class).withSelfRel();
        CollectionModel<Contract> result = CollectionModel.of(allContracts, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Contract> Contract = ContractService.findById(id);

        if(Contract.isPresent()) {
            Contract resource = Contract.get();
            Link selfLink = linkTo(ContractController.class).slash(id).withSelfRel();
            EntityModel<Contract> result = EntityModel.of(resource,selfLink);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CONTRACT_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Contract> create(@RequestBody Optional<Contract> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return ContractService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Contract> resource) {

        Optional<Contract> Contract = ContractService.findById(id);
        if(Contract.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(ContractService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CONTRACT_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Contract> Contract = ContractService.findById(id);
        if(Contract.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(ContractService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CONTRACT_NOT_FOUND);
    }
}
