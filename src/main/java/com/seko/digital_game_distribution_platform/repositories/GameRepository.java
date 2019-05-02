package com.seko.digital_game_distribution_platform.repositories;

import com.seko.digital_game_distribution_platform.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>
{ }
