package com.ezloc.app.services;

import com.ezloc.app.entities.Formula;

import java.util.List;
import java.util.Optional;

public interface FormulaService {
    List<Formula> findAll();
    Optional<Formula> add(Optional<Formula> formula);
    Optional<Formula>  findById(long id);
    String update(Long id,Optional<Formula> formula);
    String delete(long id);
}
