package com.ezloc.app.repositories;

import com.ezloc.app.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findBycar_id(Long id);
    List<Reservation> findByclient_id(Long id);
    List<Reservation> findByuser_id(Long id);
    List<Reservation> findByenterprise_id(Long id);
}
