package com.ezloc.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity(name="constructorname")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ConstructorName {
    @Id
    //@SequenceGenerator(name = "constructor_sequence",sequenceName = "constructor_sequence",allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "constructor_sequence")
    @Column(name="ID_CONSTRUCTOR", unique = true)
    private Long id;
    private String label;
    @JsonIgnore
    @OneToMany(mappedBy = "constructorName")
    private Set<Model> models;
    @JsonIgnore
    @OneToMany(mappedBy = "constructorName")
    private Set<Trim> trims;
    @JsonIgnore
    @OneToMany(mappedBy = "constructorName")
    private Set<Car> cars;
}
