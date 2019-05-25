package com.seko.digital_game_distribution_platform.services;

import com.seko.digital_game_distribution_platform.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
	String getLoggedUsername ();

    Boolean isAdmin ();

    Boolean isUser ();

    User save ( User user );
}
