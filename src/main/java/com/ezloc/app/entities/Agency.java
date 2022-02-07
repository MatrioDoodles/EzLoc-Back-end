package com.ezloc.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity(name="agency")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Agency {
    @Id
    @SequenceGenerator(name = "agency_sequence",sequenceName = "agency_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "agency_sequence")
    @Column(name="ID_AGENCY", unique = true)
    private Long id;
    private String name;
    private String description;
    private String phone;
    private String landLineNumber;
    private String fax;
    private String adress;
    private boolean primary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ENTERPRISE")
    private Enterprise enterprise;
    @OneToMany(mappedBy = "agency")
    private Set<Car> cars;
    @OneToMany(mappedBy = "agency")
    private Set<Client> clients;
}
