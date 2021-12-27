package com.ezloc.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity(name="role")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Role extends RepresentationModel<Role> {

	private static final long serialVersionUID = 1600809193487331066L;
	@Id
	@SequenceGenerator(name = "role_sequence",sequenceName = "role_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "role_sequence")
	@Column(name="ID_ROLE", unique = true)
	private Long id;
	private String label;
	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	@Getter(onMethod_ = @JsonIgnore)
	private Set<User> users;

}
