package com.ezloc.app.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity(name="invoice")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Invoice {
    @Id
    @SequenceGenerator(name = "invoice_sequence",sequenceName = "invoice_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "invoice_sequence")
    @Column(name="ID_INVOICE", unique = true)
    private Long id;
    private String checkoutType;
    private boolean paid;
    private String invoiceFile;

    @OneToOne(mappedBy = "invoice")
    private Reservation reservation;
}
