package com.ezloc.app.entities;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name="reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_RESERVATION", unique = true)
    private long id;
    private String reservationID;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLIENT")
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CAR")
    private Car car;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CONTRACT", referencedColumnName = "id")
    private Contract contract;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_INVOICE", referencedColumnName = "id")
    private Invoice invoice;
}
