package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Reservation;
import com.ezloc.app.repositories.ReservationRepository;
import com.ezloc.app.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {


    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Reservation> add(Optional<Reservation> reservation) {
        Optional<Reservation> resource = Optional.of(reservationRepository.save(reservation.get()));
        return resource;
    }

    @Override
    public Optional<Reservation> findById(long id) {
        return reservationRepository.findById(id);
    }

    @Override
    @Transactional
    public String update(Long id, Optional<Reservation> reservation) {
        Reservation resource = reservationRepository.getById(id);
        resource.setCode(Optional.ofNullable(reservation).map(c->c.get().getCode()).orElse(resource.getCode()));
        resource.setReservationDate(Optional.ofNullable(reservation).map(c->c.get().getReservationDate()).orElse(resource.getReservationDate()));
        resource.setReservationDateEnd(Optional.ofNullable(reservation).map(c->c.get().getReservationDateEnd()).orElse(resource.getReservationDateEnd()));
        resource.setReservationDuration(Optional.ofNullable(reservation).map(c->c.get().getReservationDuration()).orElse(resource.getReservationDuration()));
        resource.setMileage(Optional.ofNullable(reservation).map(c->c.get().getMileage()).orElse(resource.getMileage()));
        resource.setExtraMileage(Optional.ofNullable(reservation).map(c->c.get().getExtraMileage()).orElse(resource.getExtraMileage()));
        resource.setEndMileage(Optional.ofNullable(reservation).map(c->c.get().getEndMileage()).orElse(resource.getEndMileage()));
        resource.setStartMileage(Optional.ofNullable(reservation).map(c->c.get().getStartMileage()).orElse(resource.getStartMileage()));
        resource.setBail(Optional.ofNullable(reservation).map(c->c.get().getBail()).orElse(resource.getBail()));
        resource.setBailType(Optional.ofNullable(reservation).map(c->c.get().getBailType()).orElse(resource.getBailType()));
        resource.setBailCheckNumber(Optional.ofNullable(reservation).map(c->c.get().getBailCheckNumber()).orElse(resource.getBailCheckNumber()));
        resource.setPaid(Optional.ofNullable(reservation).map(c->c.get().isPaid()).orElse(resource.isPaid()));
        resource.setVersed(Optional.ofNullable(reservation).map(c->c.get().getVersed()).orElse(resource.getVersed()));
        resource.setRemaining(Optional.ofNullable(reservation).map(c->c.get().getRemaining()).orElse(resource.getRemaining()));
        resource.setFullAmount(Optional.ofNullable(reservation).map(c->c.get().getFullAmount()).orElse(resource.getFullAmount()));
        resource.setTheftFranchise(Optional.ofNullable(reservation).map(c->c.get().getTheftFranchise()).orElse(resource.getTheftFranchise()));
        resource.setAccidentDeductible(Optional.ofNullable(reservation).map(c->c.get().getAccidentDeductible()).orElse(resource.getAccidentDeductible()));
        resource.setCheckoutType(Optional.ofNullable(reservation).map(c->c.get().getCheckoutType()).orElse(resource.getCheckoutType()));
        resource.setCheckNumber(Optional.ofNullable(reservation).map(c->c.get().getCheckNumber()).orElse(resource.getCheckNumber()));
        resource.setCommentary(Optional.ofNullable(reservation).map(c->c.get().getCommentary()).orElse(resource.getCommentary()));
        reservationRepository.save(resource);
        return "Reservation N°"+id+" Updated Successfully";
    }

    @Override
    @Transactional
    public String delete(long id) {
        reservationRepository.deleteById(id);
        return "Reservation N°"+id+" Deleted Successfully";
    }
}
