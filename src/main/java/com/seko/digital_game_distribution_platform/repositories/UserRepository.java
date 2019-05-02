package com.seko.digital_game_distribution_platform.repositories;

import com.seko.digital_game_distribution_platform.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	User findByUsername ( String username );
}
