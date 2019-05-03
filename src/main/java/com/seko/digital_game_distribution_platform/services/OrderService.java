package com.seko.digital_game_distribution_platform.services;

import com.seko.digital_game_distribution_platform.models.Order;

import java.util.List;

public interface OrderService
{
	List<Order> findAll ();

	Order findById ( Long id );

	Order buy ( Long id );

	Boolean hasGame ( Long id );
}
