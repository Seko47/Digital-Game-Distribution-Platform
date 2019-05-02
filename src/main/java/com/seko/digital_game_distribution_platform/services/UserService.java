package com.seko.digital_game_distribution_platform.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
	String getLoggedUsername ();
}
