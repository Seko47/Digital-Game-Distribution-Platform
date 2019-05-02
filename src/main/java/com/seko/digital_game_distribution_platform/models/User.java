package com.seko.digital_game_distribution_platform.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table ( name = "users" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails
{
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Long id;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@Email
	@NotBlank
	private String email;

	private Boolean enabled;

	@ManyToMany
	@JoinTable ( name = "users_roles",
	             joinColumns = @JoinColumn ( name = "user_id" ),
	             inverseJoinColumns = @JoinColumn ( name = "role_id" ) )
	private Set<Role> roleSet;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities ()
	{
		return this.roleSet.stream ()
				.map ( role -> new SimpleGrantedAuthority ( role.getUserRole ()
						.name () ) )
				.collect ( Collectors.toSet () );
	}

	@Override
	public String getPassword ()
	{
		return this.password;
	}

	@Override
	public String getUsername ()
	{
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired ()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked ()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired ()
	{
		return true;
	}

	@Override
	public boolean isEnabled ()
	{
		return this.enabled;
	}
}
