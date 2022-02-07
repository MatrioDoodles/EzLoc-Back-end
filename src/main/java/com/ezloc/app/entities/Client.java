package com.ezloc.app.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;


@Entity(name="client")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Client{
    @Id
    @SequenceGenerator(name = "client_sequence",sequenceName = "client_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name="ID_CLIENT", unique = true)
    private Long id;
    private String name;
    private String surname;
    @Column(name="MAIL", unique = true)
    private String mail;
    private String phone;
    private String adress;
    private String code;
    private String license;
    private String cin;
    private String passport;
    private String rib;
    private boolean blacklisted;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ENTERPRISE")
    private Enterprise enterprise;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_AGENCY")
    private Agency agency;
}
