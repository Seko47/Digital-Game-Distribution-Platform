package com.seko.digital_game_distribution_platform.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seko.digital_game_distribution_platform.models.Game;
import com.seko.digital_game_distribution_platform.services.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith ( SpringRunner.class )
@WebMvcTest ( GameController.class )
public class GameControllerTest
{
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@MockBean
	private GameService gameService;

	private List<Game> gameList = new ArrayList<> ();

	@Before
	public void init ()
	{
		mockMvc = webAppContextSetup ( webApplicationContext ).build ();

		gameList.add ( Game.builder ()
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
		gameList.add ( Game.builder ()
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

	@Test
	@WithMockUser ( username = "adminTest", password = "adminTest" )
	public void test_findAllGames () throws Exception
	{
		Mockito.when ( this.gameService.findAll () )
				.thenReturn ( this.gameList );

		this.mockMvc.perform ( get ( "/games" ).accept ( MediaType.APPLICATION_JSON_VALUE ) )
				.andDo ( print () )
				.andExpect ( status ().isOk () )
				.andExpect ( jsonPath ( "$" ).isArray () )
				.andExpect ( jsonPath ( "$" ).isNotEmpty () )
				.andExpect ( jsonPath ( "$.size()" ).value ( 2 ) );
	}

	@Test
	@WithMockUser ( username = "adminTest", password = "adminTest" )
	public void test_findByIdGame () throws Exception
	{
		Mockito.when ( this.gameService.findById ( 1L ) )
				.thenReturn ( this.gameList.get ( 0 ) );

		this.mockMvc.perform ( get ( "/games/1" ).accept ( MediaType.APPLICATION_JSON_VALUE ) )
				.andDo ( print () )
				.andExpect ( status ().isOk () )
				.andExpect ( jsonPath ( "$.id" ).value ( 1L ) );
	}

	@Test
	@WithMockUser ( username = "adminTest", password = "adminTest" )
	public void test_createGame () throws Exception
	{
		Game game = Game.builder ()
				.id ( 3L )
				.name ( "Anno 1800" )
				.description ( "Kolejna odsłona popularnego cyklu strategii ekonomicznych firmy Ubisoft, którego rozwijaniem od 2011 roku zajmuje się znane z serii The Settlers studio Blue Byte Software." )
				.pathToImage ( "https://www.gry-online.pl/galeria/gry13/3538968.jpg" )
				.pathToSetupFile ( "https://notepad-plus-plus.org/repository/7.x/7.6.6/npp.7.6.6.Installer.x64.exe" )
				.author ( "Blue Byte GmbH" )
				.version ( "1.2" )
				.platformSet ( new HashSet<> ( Collections.singleton ( Game.Platform.WINDOWS ) ) )
				.releaseDate ( new Date () )
				.price ( new BigDecimal ( 180 ) )
				.build ();

		Mockito.when ( this.gameService.create ( game ) )
				.thenReturn ( game );

		this.mockMvc.perform ( post ( "/games" ).accept ( MediaType.APPLICATION_JSON_VALUE )
				.contentType ( MediaType.APPLICATION_JSON_VALUE )
				.content ( new ObjectMapper ().writeValueAsString ( game ) ) )
				.andDo ( print () )
				.andExpect ( status ().isCreated () );
	}

	@Test
	@WithMockUser ( username = "adminTest", password = "adminTest" )
	public void test_updateGame () throws Exception
	{
		Game game = Game.builder ()
				.id ( 3L )
				.name ( "Anno 1800" )
				.description ( "Kolejna odsłona popularnego cyklu strategii ekonomicznych firmy Ubisoft, którego rozwijaniem od 2011 roku zajmuje się znane z serii The Settlers studio Blue Byte Software." )
				.pathToImage ( "https://www.gry-online.pl/galeria/gry13/3538968.jpg" )
				.pathToSetupFile ( "https://notepad-plus-plus.org/repository/7.x/7.6.6/npp.7.6.6.Installer.x64.exe" )
				.author ( "Blue Byte GmbH" )
				.version ( "1.2" )
				.platformSet ( new HashSet<> ( Collections.singleton ( Game.Platform.WINDOWS ) ) )
				.releaseDate ( new Date () )
				.price ( new BigDecimal ( 180 ) )
				.build ();

		Mockito.when ( this.gameService.update ( 2L, game ) )
				.thenReturn ( game );

		this.mockMvc.perform ( put ( "/games/2" ).accept ( MediaType.APPLICATION_JSON_VALUE )
				.contentType ( MediaType.APPLICATION_JSON_VALUE )
				.content ( new ObjectMapper ().writeValueAsString ( game ) ) )
				.andDo ( print () )
				.andExpect ( status ().isOk () );
	}
}
