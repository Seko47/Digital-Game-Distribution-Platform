package com.seko.digital_game_distribution_platform.repositories;

import com.seko.digital_game_distribution_platform.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{ }
