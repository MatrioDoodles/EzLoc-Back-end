package com.ezloc.app.services;

import com.ezloc.app.entities.Metric;

import java.util.List;
import java.util.Optional;

public interface MetricService {
    List<Metric> findAll();
    Optional<Metric> add(Optional<Metric> metric);
    Optional<Metric>  findById(long id);
    Metric update(Long id,Optional<Metric> metric);
    String delete(long id);
    List<Metric> findByenterprise_id(Long id);
}
