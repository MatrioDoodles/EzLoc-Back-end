package com.ezloc.app.services;

import com.ezloc.app.entities.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyService {
    List<Agency> findAll();
    Optional<Agency> add(Optional<Agency> Agency);
    Optional<Agency>  findById(long id);
    Agency update(Long id,Optional<Agency> Agency);
    String delete(long id);
    List<Agency> findByenterprise_id(Long id);
}
