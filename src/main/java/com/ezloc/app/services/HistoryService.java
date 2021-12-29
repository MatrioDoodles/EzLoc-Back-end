package com.ezloc.app.services;

import com.ezloc.app.entities.History;

import java.util.List;
import java.util.Optional;

public interface HistoryService {
    List<History> findAll();
    Optional<History> add(Optional<History> history);
    Optional<History>  findById(long id);
    String update(Long id,Optional<History> history);
    String delete(long id);
    List<History> findByuser_id(Long Id);
}
