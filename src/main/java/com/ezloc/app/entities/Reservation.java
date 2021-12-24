package com.ezloc.app.entities;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name="reservation")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @SequenceGenerator(name = "reservation_sequence",sequenceName = "reservation_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "reservation_sequence")
    @Column(name="ID_RESERVATION", unique = true)
    private Long id;
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
