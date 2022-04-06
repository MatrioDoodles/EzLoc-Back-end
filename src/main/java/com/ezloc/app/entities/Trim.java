package com.ezloc.app.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity(name="trim")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Trim {
    @Id
    @SequenceGenerator(name = "trim_sequence",sequenceName = "trim_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "trim_sequence")
    @Column(name="ID_TRIM", unique = true)
    private Long id;
    private String label;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CONSTRUCTOR")
    private ConstructorName constructorName;
    @OneToMany(mappedBy = "trim")
    private Set<Car> cars;
}
