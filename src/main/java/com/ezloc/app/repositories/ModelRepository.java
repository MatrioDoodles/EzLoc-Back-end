package com.ezloc.app.repositories;

import com.ezloc.app.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Long> {
    List<Model> findByconstructorName_id(Long id);
}
