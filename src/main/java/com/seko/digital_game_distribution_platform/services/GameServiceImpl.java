package com.seko.digital_game_distribution_platform.services;

import com.seko.digital_game_distribution_platform.models.Game;
import com.seko.digital_game_distribution_platform.repositories.GameRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service ( "gameService" )
public class GameServiceImpl implements GameService
{
	private final GameRepository gameRepository;

	public GameServiceImpl ( GameRepository gameRepository ) {this.gameRepository = gameRepository;}

	@Override
	public List<Game> findAll ()
	{
		return this.gameRepository.findAll ();
	}

	@Override
	public Game findById ( @NotNull Long id )
	{
		return this.gameRepository.findById ( id )
				.orElse ( null );
	}

	@Override
	public void delete ( @NotNull Long id )
	{
		this.gameRepository.deleteById ( id );
	}

	@Override
	public Game create ( @NotNull Game game )
	{
		return this.gameRepository.save ( game );
	}

	@Override
	public Game update ( @NotNull Long id, @NotNull Game game )
	{
		Game gameToUpdate = findById ( id );

		if ( gameToUpdate != null )
		{
			gameToUpdate.setName ( game.getName () );
			gameToUpdate.setDescription ( game.getDescription () );
			gameToUpdate.setAuthor ( game.getAuthor () );
			gameToUpdate.setPathToImage ( game.getPathToImage () );
			gameToUpdate.setPathToSetupFile ( game.getPathToSetupFile () );
			gameToUpdate.setPlatformSet ( game.getPlatformSet () );
			gameToUpdate.setPrice ( game.getPrice () );
			gameToUpdate.setReleaseDate ( game.getReleaseDate () );
			gameToUpdate.setVersion ( game.getVersion () );

			return this.gameRepository.save ( gameToUpdate );
		}

		return create ( game );
	}
}
