package com.ezloc.app.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7325751389645179303L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_USER", unique = true)
	private long id;
	private String name;
	private String surname;
	@Column(name="MAIL", unique = true)
	private String mail;
	private String phone;
	private String adress;
	private String username;
	private String password;
	private String city;
	private String Salary;
	@Transient
	@Getter(onMethod_ = @JsonIgnore)
	private Collection<? extends GrantedAuthority> authorities;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_ROLE")
	private Role role;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ENTERPRISE")
	private Enterprise enterprise;
	@OneToMany(mappedBy = "user")
	private History history;

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
