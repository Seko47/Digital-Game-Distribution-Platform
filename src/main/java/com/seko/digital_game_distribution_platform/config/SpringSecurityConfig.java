package com.seko.digital_game_distribution_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity ( securedEnabled = true )
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Bean
	public PasswordEncoder passwordEncoder ()
	{
		return new BCryptPasswordEncoder ();
	}

	@Override
	protected void configure ( HttpSecurity httpSecurity ) throws Exception
	{
		httpSecurity.csrf ()
				.disable ()
				.httpBasic ()
				.and ()
				.authorizeRequests ()
				.anyRequest ()
				.permitAll ()
				.and ()
				.formLogin ()
				.and ()
				.logout ();
	}
}
