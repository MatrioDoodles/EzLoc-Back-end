package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Formula;
import com.ezloc.app.services.FormulaService;
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
@RequestMapping(value = "/formulas")
public class FormulaController {
    private FormulaService FormulaService;

    @Autowired
    public FormulaController(FormulaService FormulaService) {
        this.FormulaService = FormulaService;
    }

    @GetMapping
    public CollectionModel<Formula> findAll() {
        List<Formula> allFormulas = FormulaService.findAll();
        for (Formula Formula : allFormulas) {
            Long FormulaId = Formula.getId();
            Link selfLink = linkTo(FormulaController.class).slash(FormulaId).withSelfRel();
            Formula.add(selfLink);
        }


        Link link = linkTo(FormulaController.class).withSelfRel();
        CollectionModel<Formula> result = CollectionModel.of(allFormulas, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Formula> Formula = FormulaService.findById(id);

        if(Formula.isPresent()) {
            Formula resource = Formula.get();
            Link selfLink = linkTo(FormulaController.class).slash(id).withSelfRel();
            EntityModel<Formula> result = EntityModel.of(resource,selfLink);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.FORMULA_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Formula> create(@RequestBody Optional<Formula> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return FormulaService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Formula> resource) {

        Optional<Formula> Formula = FormulaService.findById(id);
        if(Formula.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(FormulaService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.FORMULA_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Formula> Formula = FormulaService.findById(id);
        if(Formula.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(FormulaService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.FORMULA_NOT_FOUND);
    }
}
