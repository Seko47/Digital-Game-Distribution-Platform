package com.seko.digital_game_distribution_platform.controllers;

import com.seko.digital_game_distribution_platform.models.Game;
import com.seko.digital_game_distribution_platform.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "/games" )
@CrossOrigin(origins = "http://localhost:4200")
public class GameController
{
	private final GameService gameService;

	public GameController ( GameService gameService ) {this.gameService = gameService;}

	@GetMapping


	@Secured ( "ROLE_ADMIN" )


	public List<Game> findAll ()
	{
		return this.gameService.findAll ();
	}

	@GetMapping ( "/{id}" )


	@Secured ( "ROLE_ADMIN" )


	public Game findById ( @PathVariable Long id )
	{
		return this.gameService.findById ( id );
	}

	@DeleteMapping ( "/{id}" )
	@ResponseStatus ( HttpStatus.NO_CONTENT )
	@Secured ( "ROLE_ADMIN" )
	public void delete ( @PathVariable Long id )
	{
		this.gameService.delete ( id );
	}

	@PostMapping
	@ResponseStatus ( HttpStatus.CREATED )
	@Secured ( "ROLE_ADMIN" )
	public Game create ( @RequestBody Game game )
	{
		return this.gameService.create ( game );
	}

	@PutMapping ( "/{id}" )
	@ResponseStatus ( HttpStatus.OK )
	@Secured ( "ROLE_ADMIN" )
	public Game update ( @PathVariable Long id, @RequestBody Game game )
	{
		return this.gameService.update ( id, game );
	}
}
