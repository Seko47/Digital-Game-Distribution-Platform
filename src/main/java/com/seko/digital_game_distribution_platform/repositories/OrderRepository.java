package com.seko.digital_game_distribution_platform.repositories;

import com.seko.digital_game_distribution_platform.models.Game;
import com.seko.digital_game_distribution_platform.models.Order;
import com.seko.digital_game_distribution_platform.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>
{
	List<Order> findAllByUser ( User user );

	Order findByGameAndUser ( Game game, User user );

	Order findByIdAndUser ( Long id, User user );
}