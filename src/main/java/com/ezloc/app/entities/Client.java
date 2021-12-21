package com.ezloc.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;


@Entity(name="client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CLIENT", unique = true)
    private long id;
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
}
