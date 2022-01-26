package com.ezloc.app.entities;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;

@Entity(name="enterprise")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Enterprise{
    @Id
    @SequenceGenerator(name = "enterprise_sequence",sequenceName = "enterprise_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "enterprise_sequence")
    @Column(name="ID_ENTERPRISE", unique = true)
    private Long id;
    private String logo;
    private String name;
    private String description;
    private String phone;
    private String bank;
    private String mail;
    private String website;
    private String landLineNumber;
    private String fax;
    private String adress;
    private String registryNumber;
    private String fiscalId;
    private String city;
    private String immatriculation;

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
    @OneToMany(mappedBy = "enterprise", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;
}
