package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Formula;
import com.ezloc.app.services.FormulaService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public List<Formula> findAll() {
        return formulaService.findAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {

        Optional<Formula> formula = formulaService.findById(id);

        if(formula.isPresent()) {
            Formula resource = formula.get();
            return ResponseEntity.status(HttpStatus.OK).body(resource);
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
