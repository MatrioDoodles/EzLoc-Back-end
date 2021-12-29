package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Metric;
import com.ezloc.app.repositories.MetricRepository;
import com.ezloc.app.services.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MetricServiceImpl implements MetricService {


    private final MetricRepository metricRepository;

    @Autowired
    public MetricServiceImpl(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    @Override
    public List<Metric> findAll() {
        return metricRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Metric> add(Optional<Metric> metric) {
        return Optional.of(metricRepository.save(metric.get()));
    }

    @Override
    public Optional<Metric> findById(long id) {
        return metricRepository.findById(id);
    }

    @Override
    @Transactional
    public String update(Long id, Optional<Metric> metric) {
        Metric resource = metricRepository.getById(id);
        resource.setMertricName(Optional.ofNullable(metric).map(c->c.get().getMertricName()).orElse(resource.getMertricName()));
        resource.setMetric(Optional.ofNullable(metric).map(c->c.get().getMetric()).orElse(resource.getMetric()));
        return "Metric N° "+id+" Updated Successfully";
    }

    @Override
    @Transactional
    public String delete(long id) {
        metricRepository.deleteById(id);
        return "Metric N° "+id+" Deleted Successfully";
    }

    @Override
    public List<Metric> findByenterprise_id(Long id) {
        return metricRepository.findByenterprise_id(id);
    }
}
