package com.seko.digital_game_distribution_platform.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table ( name = "roles" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role
{
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Long id;

	@Enumerated ( EnumType.STRING )
	private UserRole userRole;

	public enum UserRole
	{
		ROLE_ADMIN, ROLE_USER
	}
}
