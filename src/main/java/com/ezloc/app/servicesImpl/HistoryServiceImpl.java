package com.ezloc.app.servicesImpl;


import com.ezloc.app.entities.Car;
import com.ezloc.app.entities.History;
import com.ezloc.app.repositories.HistoryRepository;
import com.ezloc.app.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {


    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public List<History> findAll() {
        return historyRepository.findAll();
    }

    @Override
    public Optional<History> add(Optional<History> history) {
        Optional<History> resource = Optional.of(historyRepository.save(history.get()));
        return resource;
    }

    @Override
    public Optional<History> findById(long id) {
        return historyRepository.findById(id);
    }

    @Override
    public String update(Long id, Optional<History> history) {
        History resource = historyRepository.getById(id);
        resource.setEntity(Optional.ofNullable(history).map(c->c.get().getEntity()).orElse(resource.getEntity()));
        resource.setAction(Optional.ofNullable(history).map(c->c.get().getAction()).orElse(resource.getAction()));
        resource.setActionDate(Optional.ofNullable(history).map(c->c.get().getActionDate()).orElse(resource.getActionDate()));
        resource.setSeen(Optional.ofNullable(history).map(c->c.get().isSeen()).orElse(resource.isSeen()));
        resource.setSeenFromNotificationZone(Optional.ofNullable(history).map(c->c.get().isSeenFromNotificationZone()).orElse(resource.isSeenFromNotificationZone()));

        historyRepository.save(resource);
        return "Car N "+id+" updated successfully";
    }

    @Override
    public String delete(long id) {
        return "History N° "+id+"Deleted Successfully";
    }
}
