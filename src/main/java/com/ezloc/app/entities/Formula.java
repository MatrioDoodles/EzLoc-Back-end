package com.ezloc.app.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity(name="formula")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_FORMULA", unique = true)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ENTERPRISE", referencedColumnName = "id")
    private Enterprise enterprise;
}
