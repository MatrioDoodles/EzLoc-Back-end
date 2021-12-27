package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Reservation;
import com.ezloc.app.services.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public Optional<Reservation> add(Optional<Reservation> reservation) {
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> findById(long id) {
        return Optional.empty();
    }

    @Override
    public String update(Long id, Optional<Reservation> reservation) {
        return null;
    }

    @Override
    public String delete(long id) {
        return null;
    }
}
