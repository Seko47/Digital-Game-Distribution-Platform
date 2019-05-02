package com.seko.digital_game_distribution_platform.services;

import com.seko.digital_game_distribution_platform.models.Game;

import java.util.List;

public interface GameService
{
	List<Game> findAll ();

	Game findById ( Long id );

	void delete ( Long id );

	Game create ( Game game );

	Game update ( Long id, Game game );
}
