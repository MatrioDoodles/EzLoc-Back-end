package com.ezloc.app.services;

import com.ezloc.app.entities.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    List<Contract> findAll();
    Optional<Contract> add(Optional<Contract> contract);
    Optional<Contract>  findById(long id);
    String update(Long id,Optional<Contract> contract);
    String delete(long id);
}
