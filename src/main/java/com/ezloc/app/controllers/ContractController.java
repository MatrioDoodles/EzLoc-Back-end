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
    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public CollectionModel<Contract> findAll() {
        List<Contract> allContracts = contractService.findAll();
        for (Contract contract : allContracts) {
            Long contractId = contract.getId();
            Link selfLink = linkTo(ContractController.class).slash(contractId).withSelfRel();
            contract.add(selfLink);
        }


        Link link = linkTo(ContractController.class).withSelfRel();
        return CollectionModel.of(allContracts, link);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        Optional<Contract> contract = contractService.findById(id);

        if(contract.isPresent()) {
            Contract resource = contract.get();
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
        return contractService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Contract> resource) {

        Optional<Contract> contract = contractService.findById(id);
        if(contract.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(contractService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CONTRACT_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Contract> contract = contractService.findById(id);
        if(contract.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(contractService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.CONTRACT_NOT_FOUND);
    }
}
