package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.History;
import com.ezloc.app.services.HistoryService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/history")
public class HistoryController {
    private HistoryService HistoryService;

    @Autowired
    public HistoryController(HistoryService HistoryService) {
        this.HistoryService = HistoryService;
    }

    @GetMapping
    public List<History> findAll() {

        return HistoryService.findAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<History> History = HistoryService.findById(id);

        if(History.isPresent()) {
            History resource = History.get();
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.HISTORY_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<History> create(@RequestBody Optional<History> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return HistoryService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Optional<History> resource) {

        Optional<History> History = HistoryService.findById(id);
        if(History.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(HistoryService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.HISTORY_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<History> History = HistoryService.findById(id);
        if(History.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(HistoryService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.HISTORY_NOT_FOUND);
    }
}
