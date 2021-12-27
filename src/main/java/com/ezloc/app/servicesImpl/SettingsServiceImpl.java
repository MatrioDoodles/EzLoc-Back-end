package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Settings;
import com.ezloc.app.services.SettingsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettingsServiceImpl implements SettingsService {
    @Override
    public List<Settings> findAll() {
        return null;
    }

    @Override
    public Optional<Settings> add(Optional<Settings> settings) {
        return Optional.empty();
    }

    @Override
    public Optional<Settings> findById(long id) {
        return Optional.empty();
    }

    @Override
    public String update(Long id, Optional<Settings> settings) {
        return null;
    }

    @Override
    public String delete(long id) {
        return null;
    }
}
