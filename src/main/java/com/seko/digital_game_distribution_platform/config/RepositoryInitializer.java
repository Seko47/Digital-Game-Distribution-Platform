package com.seko.digital_game_distribution_platform.config;

import com.seko.digital_game_distribution_platform.models.Game;
import com.seko.digital_game_distribution_platform.models.Role;
import com.seko.digital_game_distribution_platform.models.User;
import com.seko.digital_game_distribution_platform.repositories.GameRepository;
import com.seko.digital_game_distribution_platform.repositories.RoleRepository;
import com.seko.digital_game_distribution_platform.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoryInitializer
{
	private final UserRepository  userRepository;
	private final RoleRepository  roleRepository;
	private final GameRepository  gameRepository;
	private final PasswordEncoder passwordEncoder;

	public RepositoryInitializer ( UserRepository userRepository, RoleRepository roleRepository, GameRepository gameRepository, PasswordEncoder passwordEncoder )
	{
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.gameRepository = gameRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	InitializingBean init ()
	{
		return () -> {
			Role roleAdmin = new Role ( 1L, Role.UserRole.ROLE_ADMIN );
			Role roleUser  = new Role ( 2L, Role.UserRole.ROLE_USER );

			User userAdmin = new User ();
			userAdmin.setId ( 1L );
			userAdmin.setUsername ( "admin" );
			userAdmin.setPassword ( this.passwordEncoder.encode ( "admin" ) );
			userAdmin.setEmail ( "admin@ema.il" );
			userAdmin.setEnabled ( true );
			userAdmin.setRoleSet ( new HashSet<> ( Arrays.asList ( roleAdmin, roleUser ) ) );

			User userUser = new User ();
			userUser.setId ( 2L );
			userUser.setUsername ( "user" );
			userUser.setPassword ( this.passwordEncoder.encode ( "user" ) );
			userUser.setEmail ( "user@ema.il" );
			userUser.setEnabled ( true );
			userUser.setRoleSet ( new HashSet<> ( Collections.singletonList ( roleUser ) ) );

			if ( this.roleRepository.findAll ()
					.isEmpty () )
			{
				this.roleRepository.saveAll ( Arrays.asList ( roleAdmin, roleUser ) );
			}
			if ( this.userRepository.findAll ()
					.isEmpty () )
			{
				this.userRepository.saveAll ( Arrays.asList ( userAdmin, userUser ) );
			}
			if ( this.gameRepository.findAll ()
					.isEmpty () )
			{
				this.gameRepository.save ( Game.builder ()
						.id ( 1L )
						.name ( "Assassin's Creed: Unity" )
						.description ( "Piąta pełnoprawna część kultowego cyklu Assassin’s Creed, zapoczątkowanego przez koncern Ubisoft w 2007 roku. Jest to jednocześnie pierwsza odsłona serii zaprojektowana wyłącznie z myślą o posiadaczach PC i konsol ósmej generacji." )
						.pathToImage ( "https://www.gry-online.pl/galeria/gry13/522717495d.jpg" )
						.pathToSetupFile ( "https://notepad-plus-plus.org/repository/7.x/7.6.6/npp.7.6.6.Installer.x64.exe" )
						.author ( "Ubisoft" )
						.version ( "1.4" )
						.platformSet ( new HashSet<> ( Collections.singleton ( Game.Platform.WINDOWS ) ) )
						.releaseDate ( new Date () )
						.price ( new BigDecimal ( 200 ) )
						.build () );
				this.gameRepository.save ( Game.builder ()
						.id ( 2L )
						.name ( "Imperator: Rome" )
						.description ( "Stworzona przez Paradox Interactive strategia na dużą skalę (grand strategy game), pozwalająca pokierować wybranym państwem w epoce starożytności, począwszy od okresu świetności Aten." )
						.pathToImage ( "https://www.gry-online.pl/galeria/gry13/595207218.jpg" )
						.pathToSetupFile ( "https://notepad-plus-plus.org/repository/7.x/7.6.6/npp.7.6.6.Installer.x64.exe" )
						.author ( "Paradox Interactive" )
						.version ( "1.2" )
						.platformSet ( new HashSet<> ( Collections.singleton ( Game.Platform.WINDOWS ) ) )
						.releaseDate ( new Date () )
						.price ( new BigDecimal ( 240 ) )
						.build () );
			}
		};
	}
}
