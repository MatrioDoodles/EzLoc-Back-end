package com.ezloc.app.entities;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity(name="settings")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Settings{
    @Id
    @SequenceGenerator(name = "settings_sequence",sequenceName = "settings_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "settings_sequence")
    @Column(name="ID_SETTINGS", unique = true)
    private Long id;
    private boolean tva;
    private Long tvaValue;
    private String currency;
    private String acronym;
    private String clientPrefix;
    private String carPrefix;
    private String reservationPrefix;
    private String invoicePrefix;
    private String contractPrefix;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ENTERPRISE", referencedColumnName = "ID_ENTERPRISE")
    private Enterprise enterprise;

}
