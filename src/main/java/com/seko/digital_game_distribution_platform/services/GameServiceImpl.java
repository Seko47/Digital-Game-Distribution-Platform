package com.seko.digital_game_distribution_platform.services;

import com.seko.digital_game_distribution_platform.models.Game;
import com.seko.digital_game_distribution_platform.repositories.GameRepository;
import com.seko.digital_game_distribution_platform.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service ( "gameService" )
public class GameServiceImpl implements GameService
{
    private final GameRepository gameRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public GameServiceImpl ( GameRepository gameRepository, OrderRepository orderRepository )
    {
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List< Game > findAll ()
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
    @Transactional
    public void delete ( @NotNull Long id )
    {
        System.out.println ("id = "+id);
        Game game = findById ( id );
        if ( game != null )
        {
            System.out.println ("game = "+game.getId ());

            this.orderRepository.deleteAllByGame ( game );
            this.gameRepository.delete ( game );
        }
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
            gameToUpdate.setPlatform ( game.getPlatform () );
            gameToUpdate.setPrice ( game.getPrice () );
            gameToUpdate.setReleaseDate ( game.getReleaseDate () );
            gameToUpdate.setVersion ( game.getVersion () );

            return this.gameRepository.save ( gameToUpdate );
        }

        return create ( game );
    }
}
