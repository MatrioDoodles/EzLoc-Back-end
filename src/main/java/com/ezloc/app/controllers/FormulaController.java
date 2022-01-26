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

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/formulas")
public class FormulaController {

    private final FormulaService formulaService;

    @Autowired
    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @GetMapping
    public CollectionModel<Formula> findAll() {
        List<Formula> allFormulas = formulaService.findAll();
        for (Formula formula : allFormulas) {
            Long formulaId = formula.getId();
            Link selfLink = linkTo(FormulaController.class).slash(formulaId).withSelfRel();
            formula.add(selfLink);
        }


        Link link = linkTo(FormulaController.class).withSelfRel();
        return CollectionModel.of(allFormulas, link);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        Optional<Formula> formula = formulaService.findById(id);

        if(formula.isPresent()) {
            Formula resource = formula.get();
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
        return formulaService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Formula> resource) {

        Optional<Formula> formula = formulaService.findById(id);
        if(formula.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(formulaService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.FORMULA_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Formula> formula = formulaService.findById(id);
        if(formula.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(formulaService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.FORMULA_NOT_FOUND);
    }
}
