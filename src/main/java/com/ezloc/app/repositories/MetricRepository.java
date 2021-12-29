package com.ezloc.app.repositories;

import com.ezloc.app.entities.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
    List<Metric> findByenterprise_id(Long id);
}
