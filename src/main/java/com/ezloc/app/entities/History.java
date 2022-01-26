package com.ezloc.app.entities;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="history")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class History{

    @Id
    @SequenceGenerator(name = "history_sequence",sequenceName = "history_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "history_sequence")
    @Column(name="ID_HISTORY", unique = true)
    private Long id;
    private String entity;
    private String action;
    private LocalDate actionDate;
    private boolean seen;
    private boolean seenFromNotificationZone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER")
    private User user;
}
