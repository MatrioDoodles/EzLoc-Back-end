package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Metric;
import com.ezloc.app.services.MetricService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetricServiceImpl implements MetricService {
    @Override
    public List<Metric> findAll() {
        return null;
    }

    @Override
    public Optional<Metric> add(Optional<Metric> metric) {
        return Optional.empty();
    }

    @Override
    public Optional<Metric> findById(long id) {
        return Optional.empty();
    }

    @Override
    public String update(Long id, Optional<Metric> metric) {
        return null;
    }

    @Override
    public String delete(long id) {
        return null;
    }
}
