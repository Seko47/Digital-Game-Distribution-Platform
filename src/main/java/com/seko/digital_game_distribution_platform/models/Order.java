package com.seko.digital_game_distribution_platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table ( name = "orders" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order
{
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Long id;

	@ManyToOne
	private Game game;

	@ManyToOne
	private User user;

	@Temporal ( TemporalType.TIMESTAMP )
	private Date orderDate;
}
