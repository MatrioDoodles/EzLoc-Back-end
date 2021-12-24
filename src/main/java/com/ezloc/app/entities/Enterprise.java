package com.ezloc.app.entities;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity(name="enterprise")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Enterprise {
    @Id
    @SequenceGenerator(name = "enterprise_sequence",sequenceName = "enterprise_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "enterprise_sequence")
    @Column(name="ID_ENTERPRISE", unique = true)
    private Long id;
    private String logo;
    private String name;
    private String description;
    private String phone;
    private String adress;


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
