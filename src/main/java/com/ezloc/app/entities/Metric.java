package com.ezloc.app.entities;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity(name="metric")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Metric {
    @Id
    @SequenceGenerator(name = "metric_sequence",sequenceName = "metric_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "metric_sequence")
    @Column(name="ID_METRIC", unique = true)
    private Long id;
    private String mertricName;
    private long metric;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ENTERPRISE")
    private Enterprise enterprise;

}
