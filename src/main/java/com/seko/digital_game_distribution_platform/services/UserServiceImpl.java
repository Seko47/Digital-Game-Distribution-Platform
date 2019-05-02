package com.seko.digital_game_distribution_platform.services;

import com.seko.digital_game_distribution_platform.models.User;
import com.seko.digital_game_distribution_platform.repositories.RoleRepository;
import com.seko.digital_game_distribution_platform.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service ( "userService" )
public class UserServiceImpl implements UserService
{
	private final PasswordEncoder passwordEncoder;
	private final UserRepository  userRepository;
	private final RoleRepository  roleRepository;

	public UserServiceImpl ( PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository )
	{
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException
	{
		User user = this.userRepository.findByUsername ( username );

		if ( user == null )
		{
			throw new UsernameNotFoundException ( "User not found" );
		}

		return user;
	}

	@Override
	public String getLoggedUsername ()
	{
		User user = (User) SecurityContextHolder.getContext ()
				.getAuthentication ()
				.getPrincipal ();
		
		return user.getUsername ();
	}
}
