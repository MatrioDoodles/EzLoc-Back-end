package com.ezloc.app.entities;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="contract")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Contract{
    @Id
    @SequenceGenerator(name = "contract_sequence",sequenceName = "contract_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "contract_sequence")
    @Column(name="ID_CONTRACT", unique = true)
    private Long id;
    private String code;
    private String contractFile;
    private String contractFileName;
    private LocalDate dateOfCreation;



    @OneToOne(mappedBy = "contract")
    private Reservation reservation;



}
