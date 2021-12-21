package com.ezloc.app.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity(name="enterprise")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ENTERPRISE", unique = true)
    private long id;
    @OneToOne(mappedBy = "enterprise")
    private Settings settings;
    @OneToMany(mappedBy = "enterprise")
    private Set<Client> clients;
    @OneToMany(mappedBy = "enterprise")
    private Set<Car> cars;
    @OneToMany(mappedBy = "enterprise")
    private Set<Metric> metrics;
    @OneToMany(mappedBy = "enterprise")
    private Set<User> users;
    @OneToOne(mappedBy = "enterprise")
    private Formula formula;
}
