package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Settings;
import com.ezloc.app.repositories.SettingsRepository;
import com.ezloc.app.services.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;

    @Autowired
    public SettingsServiceImpl(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @Override
    public List<Settings> findAll() {
        return settingsRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Settings> add(Optional<Settings> settings) {
        return Optional.of(settingsRepository.save(settings.get()));
    }

    @Override
    public Optional<Settings> findById(long id) {
        return settingsRepository.findById(id);
    }

    @Override
    @Transactional
    public Settings update(Long id, Optional<Settings> settings) {
        Settings resource = settingsRepository.getById(id);
        resource.setAcronym(Optional.ofNullable(settings).map(c->c.get().getAcronym()).orElse(resource.getAcronym()));
        resource.setCarPrefix(Optional.ofNullable(settings).map(c->c.get().getCarPrefix()).orElse(resource.getCarPrefix()));
        resource.setClientPrefix(Optional.ofNullable(settings).map(c->c.get().getClientPrefix()).orElse(resource.getClientPrefix()));
        resource.setContractPrefix(Optional.ofNullable(settings).map(c->c.get().getContractPrefix()).orElse(resource.getContractPrefix()));
        resource.setReservationPrefix(Optional.ofNullable(settings).map(c->c.get().getReservationPrefix()).orElse(resource.getReservationPrefix()));
        resource.setInvoicePrefix(Optional.ofNullable(settings).map(c->c.get().getInvoicePrefix()).orElse(resource.getInvoicePrefix()));
        resource.setCurrency(Optional.ofNullable(settings).map(c->c.get().getCurrency()).orElse(resource.getCurrency()));
        resource.setTvaValue(Optional.ofNullable(settings).map(c->c.get().getTvaValue()).orElse(resource.getTvaValue()));
        resource.setTva(Optional.ofNullable(settings).map(c->c.get().isTva()).orElse(resource.isTva()));
        return settingsRepository.save(resource);
    }

    @Override
    @Transactional
    public String delete(long id) {
        settingsRepository.deleteById(id);
        return "Settings N° "+id+" Deleted Successfully";
    }
}
