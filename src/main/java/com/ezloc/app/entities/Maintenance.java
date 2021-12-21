package com.ezloc.app.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity(name="maintenance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MAINTENANCE", unique = true)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CAR", referencedColumnName = "id")
    private Car car;

}
