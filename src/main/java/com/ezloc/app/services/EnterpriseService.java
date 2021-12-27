package com.ezloc.app.services;

import com.ezloc.app.entities.Enterprise;

import java.util.List;
import java.util.Optional;

public interface EnterpriseService {
    List<Enterprise> findAll();
    Optional<Enterprise> add(Optional<Enterprise> enterprise);
    Optional<Enterprise>  findById(long id);
    String update(Long id,Optional<Enterprise> enterprise);
    String delete(long id);
}
