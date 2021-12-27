package com.ezloc.app.entities;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="user")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends RepresentationModel<User> implements UserDetails {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7325751389645179303L;
	@Id
	@SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
	@Column(name="ID_USER", unique = true)
	private Long id;
	private String name;
	private String surname;
	@Column(name="MAIL", unique = true)
	private String mail;
	private String phone;
	private String adress;
	private String username;
	private String password;
	private String city;
	private boolean activated;

	@Transient
	@Getter(onMethod_ = @JsonIgnore)
	private Collection<? extends GrantedAuthority> authorities;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_ROLE")
	private Role role;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ENTERPRISE")
	private Enterprise enterprise;
	@OneToMany(mappedBy = "user")
	private Set<History> histories;

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}


	
}
