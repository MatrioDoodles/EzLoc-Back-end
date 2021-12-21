package com.ezloc.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity(name="role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Role implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1600809193487331066L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_ROLE", unique = true)
	private long id;
	private String label;
	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	@Getter(onMethod_ = @JsonIgnore)
	private Set<User> users;

}
