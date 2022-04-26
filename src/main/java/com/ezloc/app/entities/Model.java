package com.ezloc.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity(name="model")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Model {

    @Id
   // @SequenceGenerator(name = "model_sequence",sequenceName = "model_sequence",allocationSize = 1)
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "model_sequence")
    @Column(name="ID_MODEL", unique = true)
    private Long id;
    private String label;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CONSTRUCTOR")
    private ConstructorName constructorName;
    @JsonIgnore
    @OneToMany(mappedBy = "model")
    private Set<Car> cars;
}
