package com.seko.digital_game_distribution_platform.controllers;

import com.seko.digital_game_distribution_platform.models.Game;
import com.seko.digital_game_distribution_platform.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "/games" )
public class GameController
{
	private final GameService gameService;

	public GameController ( GameService gameService ) {this.gameService = gameService;}

	@GetMapping
	public List<Game> findAll ()
	{
		return this.gameService.findAll ();
	}

	@GetMapping ( "/{id}" )
	public Game findById ( @PathVariable Long id )
	{
		return this.gameService.findById ( id );
	}

	@DeleteMapping ( "/{id}" )
	@ResponseStatus ( HttpStatus.NO_CONTENT )
	public void delete ( @PathVariable Long id )
	{
		this.gameService.delete ( id );
	}

	@PostMapping
	@ResponseStatus ( HttpStatus.CREATED )
	public Game create ( @RequestBody Game game )
	{
		return this.gameService.create ( game );
	}

	@PutMapping ( "/{id}" )
	@ResponseStatus ( HttpStatus.OK )
	public Game update ( @PathVariable Long id, @RequestBody Game game )
	{
		return this.gameService.update ( id, game );
	}
}
