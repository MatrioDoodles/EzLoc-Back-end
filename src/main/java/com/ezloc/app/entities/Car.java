package com.ezloc.app.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="car")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Car {
    @Id
    @SequenceGenerator(name = "car_sequence",sequenceName = "car_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "car_sequence")
    @Column(name="ID_CAR", unique = true)
    private Long id;
    private String color;
    private String year;
    private String category;
    private String fuel;
    private Long mileage;
    private String gearbox;
    private String registration;
    private boolean available;
    private int fiscalPower;
    @JsonIgnore
    @OneToMany(mappedBy = "car",fetch = FetchType.LAZY)
    private Set<Reservation> reservations= new HashSet<Reservation>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MAINTENANCE", referencedColumnName = "ID_MAINTENANCE")
    private Maintenance maintenance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ENTERPRISE")
    private Enterprise enterprise;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_AGENCY")
    private Agency agency;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CONSTRUCTOR")
    private ConstructorName constructorName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TRIM")
    private Trim trim;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODEL")
    private Model model;
}
