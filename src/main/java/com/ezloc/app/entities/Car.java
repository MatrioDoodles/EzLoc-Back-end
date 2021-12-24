package com.ezloc.app.entities;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="car")
@Data
@Accessors(chain = true)
@RequiredArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Car {
    @Id
    @SequenceGenerator(name = "car_sequence",sequenceName = "car_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "car_sequence")
    @Column(name="ID_CAR", unique = true)
    private Long id;
    @NonNull
    private String constructor;
    @NonNull
    private String model;
    @NonNull
    private String color;
    @NonNull
    private String year;
    @NonNull
    private String category;
    @NonNull
    private String trim;
    @NonNull
    private String fuel;
    @NonNull
    private Long mileage;
    @NonNull
    private String gearbox;
    @NonNull
    private String registration;
    @NonNull
    private boolean available;
    @NonNull
    private int fiscalPower;
    @OneToMany(mappedBy = "car",fetch = FetchType.LAZY)
    private Set<Reservation> reservations= new HashSet<Reservation>();
    @OneToOne(mappedBy = "car")
    private Maintenance maintenance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ENTERPRISE")
    private Enterprise enterprise;
}
