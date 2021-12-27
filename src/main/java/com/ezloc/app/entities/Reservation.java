package com.ezloc.app.entities;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="reservation")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation extends RepresentationModel<Reservation> {
    @Id
    @SequenceGenerator(name = "reservation_sequence",sequenceName = "reservation_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "reservation_sequence")
    @Column(name="ID_RESERVATION", unique = true)
    private Long id;
    private String code;
    private LocalDate reservationDate;
    private LocalDate reservationDateEnd;
    private Long reservationDuration;
    private Long mileage;
    private Long extraMileage;
    private Long startMileage;
    private Long endMileage;
    private Long bail;
    private String bailType;
    private String bailCheckNumber;
    private boolean paid;
    private Long versed;
    private Long remaining;
    private Long fullAmount;
    private Long theftFranchise;
    private Long accidentDeductible;
    private String checkoutType;
    private String checkNumber;
    private String commentary;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLIENT")
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CAR")
    private Car car;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CONTRACT", referencedColumnName = "ID_CONTRACT")
    private Contract contract;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_INVOICE", referencedColumnName = "ID_INVOICE")
    private Invoice invoice;
}
