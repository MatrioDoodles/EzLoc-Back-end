package com.ezloc.app.repositories;

import com.ezloc.app.entities.Trim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrimRepository extends JpaRepository<Trim,Long> {
    List<Trim> findByconstructorName_id(Long id);
}
