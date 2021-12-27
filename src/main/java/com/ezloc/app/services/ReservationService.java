package com.ezloc.app.services;

import com.ezloc.app.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAll();
    Optional<Reservation> add(Optional<Reservation> reservation);
    Optional<Reservation>  findById(long id);
    String update(Long id,Optional<Reservation> reservation);
    String delete(long id);
}
