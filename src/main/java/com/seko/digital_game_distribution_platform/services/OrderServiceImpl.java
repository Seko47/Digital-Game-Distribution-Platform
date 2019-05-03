package com.seko.digital_game_distribution_platform.services;

import com.seko.digital_game_distribution_platform.models.Order;
import com.seko.digital_game_distribution_platform.models.User;
import com.seko.digital_game_distribution_platform.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service ( "orderService" )
public class OrderServiceImpl implements OrderService
{
	private final UserService     userService;
	private final OrderRepository orderRepository;
	private final GameService     gameService;

	public OrderServiceImpl ( UserService userService, OrderRepository orderRepository, GameService gameService )
	{
		this.userService = userService;
		this.orderRepository = orderRepository;
		this.gameService = gameService;
	}

	@Override
	public List<Order> findAll ()
	{
		return this.orderRepository.findAllByUser ( (User) this.userService.loadUserByUsername ( this.userService.getLoggedUsername () ) );
	}

	@Override
	public Order findById ( @NotNull Long id )
	{
		return this.orderRepository.findByIdAndUser ( id, (User) this.userService.loadUserByUsername ( this.userService.getLoggedUsername () ) );
	}

	@Override
	public Order buy ( @NotNull Long id )
	{
		if ( !hasGame ( id ) )
		{
			Order order = Order.builder ()
					.game ( this.gameService.findById ( id ) )
					.user ( (User) this.userService.loadUserByUsername ( this.userService.getLoggedUsername () ) )
					.orderDate ( new Date () )
					.build ();

			return this.orderRepository.save ( order );
		}

		return null;
	}

	@Override
	public Boolean hasGame ( @NotNull Long id )
	{
		Order order = this.orderRepository.findByGameAndUser ( this.gameService.findById ( id ), (User) this.userService.loadUserByUsername ( this.userService.getLoggedUsername () ) );

		return order != null;
	}
}
