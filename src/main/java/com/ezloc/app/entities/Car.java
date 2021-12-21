package com.ezloc.app.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CAR", unique = true)
    private long id;
    private String constructor;
    private String model;
    private String color;
    private String year;
    private String category;
    private String trim;
    private String fuel;
    private String mileage;
    private String gearbox;
    private String registration;
    @OneToMany(mappedBy = "car",fetch = FetchType.LAZY)
    private Set<Reservation> reservations= new HashSet<Reservation>();
    @OneToOne(mappedBy = "car")
    private Maintenance maintenance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ENTERPRISE")
    private Enterprise enterprise;
}
