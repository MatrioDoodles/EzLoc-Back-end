package com.ezloc.app.services;

import com.ezloc.app.entities.Settings;

import java.util.List;
import java.util.Optional;

public interface SettingsService {
    List<Settings> findAll();
    Optional<Settings> add(Optional<Settings> settings);
    Optional<Settings>  findById(long id);
    Settings update(Long id,Optional<Settings> settings);
    String delete(long id);
}
