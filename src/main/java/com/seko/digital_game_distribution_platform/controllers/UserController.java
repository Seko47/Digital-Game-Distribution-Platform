package com.seko.digital_game_distribution_platform.controllers;

import com.seko.digital_game_distribution_platform.models.User;
import com.seko.digital_game_distribution_platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin ( "http://localhost:4200" )
public class UserController
{
	private final UserService     userService;
	private final PasswordEncoder passwordEncoder;

	public UserController ( UserService userService, PasswordEncoder passwordEncoder )
	{
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@RequestMapping ( "/login" )
	public boolean login ( @RequestBody User user )
	{
		UserDetails userDetails = this.userService.loadUserByUsername ( user.getUsername () );
		return user.getUsername ()
				.equals ( userDetails.getUsername () ) && passwordEncoder.matches ( user.getPassword (), userDetails.getPassword () );
	}

	@RequestMapping ( "/user" )
	public Principal user ( HttpServletRequest request )
	{
		String authToken = request.getHeader ( "Authorization" )
				.substring ( "Basic".length () )
				.trim ();

		return () -> new String ( Base64.getDecoder ()
				.decode ( authToken ) ).split ( ":" )[ 0 ];
	}
}
