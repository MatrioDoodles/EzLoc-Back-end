package com.ezloc.app.repositories;


import com.ezloc.app.entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Long> {
    List<Agency> findByenterprise_id(Long id);
}
