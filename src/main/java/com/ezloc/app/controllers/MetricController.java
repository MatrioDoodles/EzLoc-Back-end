package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Metric;
import com.ezloc.app.services.MetricService;
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
@RequestMapping(value = "/metrics")
public class MetricController {
    private MetricService MetricService;

    @Autowired
    public MetricController(MetricService MetricService) {
        this.MetricService = MetricService;
    }

    @GetMapping
    public CollectionModel<Metric> findAll() {
        List<Metric> allMetrics = MetricService.findAll();
        for (Metric Metric : allMetrics) {
            Long MetricId = Metric.getId();
            Link selfLink = linkTo(MetricController.class).slash(MetricId).withSelfRel();
            Metric.add(selfLink);
        }


        Link link = linkTo(MetricController.class).withSelfRel();
        CollectionModel<Metric> result = CollectionModel.of(allMetrics, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Metric> Metric = MetricService.findById(id);

        if(Metric.isPresent()) {
            Metric resource = Metric.get();
            Link selfLink = linkTo(MetricController.class).slash(id).withSelfRel();
            EntityModel<Metric> result = EntityModel.of(resource,selfLink);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.METRIC_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Metric> create(@RequestBody Optional<Metric> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return MetricService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Metric> resource) {

        Optional<Metric> Metric = MetricService.findById(id);
        if(Metric.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(MetricService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.METRIC_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Metric> Metric = MetricService.findById(id);
        if(Metric.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(MetricService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.METRIC_NOT_FOUND);
    }
}
