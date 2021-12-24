package com.ezloc.app.entities;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="maintenance")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Maintenance {
    @Id
    @SequenceGenerator(name = "maintenance_sequence",sequenceName = "maintenance_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "maintenance_sequence")
    @Column(name="ID_MAINTENANCE", unique = true)
    private Long id;
    private String assurance;
    private LocalDate assuranceEndDate;
    private Long oilChangeKm;
    private Long lastMileage;
    private LocalDate technicalVisit ;
    private LocalDate vignette;
    private boolean  vignettePaid;
    private boolean  assurancePaid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CAR", referencedColumnName = "id")
    private Car car;

}
