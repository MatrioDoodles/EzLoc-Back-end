package com.ezloc.app.services;

import com.ezloc.app.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAll();
    Optional<Reservation> add(Optional<Reservation> reservation);
    Optional<Reservation>  findById(long id);
    Reservation update(Long id,Optional<Reservation> reservation);
    String delete(long id);
    List<Reservation> findBycar_id(Long id);
    List<Reservation> findByclient_id(Long id);
    List<Reservation> findByuser_id(Long id);
    List<Reservation> findByenterprise_id(Long id);
}
