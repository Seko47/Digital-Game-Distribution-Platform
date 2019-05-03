package com.seko.digital_game_distribution_platform.controllers;

import com.seko.digital_game_distribution_platform.models.Order;
import com.seko.digital_game_distribution_platform.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "/orders" )
@Secured ( "ROLE_USER" )
public class OrderController
{
	private final OrderService orderService;

	public OrderController ( OrderService orderService ) {this.orderService = orderService;}

	@GetMapping
	public List<Order> findAll ()
	{
		return this.orderService.findAll ();
	}

	@GetMapping ( "/{id}" )
	public Order findById ( @PathVariable Long id )
	{
		return this.orderService.findById ( id );
	}

	@GetMapping ( "/buy/{id}" )
	@ResponseStatus ( HttpStatus.CREATED )
	public Order buy ( @PathVariable Long id )
	{
		return this.orderService.buy ( id );
	}

	@GetMapping ( "/has/{id}" )
	@ResponseStatus ( HttpStatus.OK )
	public Boolean hasUserGame ( @PathVariable Long id )
	{
		return this.orderService.hasGame ( id );
	}
}
