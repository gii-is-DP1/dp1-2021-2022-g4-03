package org.springframework.samples.petclinic.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.board.BoardController;
import org.springframework.samples.petclinic.board.BoardService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Controller
public class GameController {
    
	@Autowired
	private GameService gameService;

    @Autowired
	private UserDwarfService userDwarfService;

    @Autowired
	private BoardService boardService;

	@Autowired
	private BoardController boardController;

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @Getter
	@Setter
	@EqualsAndHashCode
	public class Wrapper {

		// UserDwarf userDwarf;

		// List<String> roles = new ArrayList<>();
	}

	@GetMapping(value = "/game/new")
	public String createGame() {
		// Hasta que no tengamos currentUser creamos la partida con el user frabotrom
		UserDwarf player= userDwarfService.findUserDwarfByUsername2(CurrentUser.getCurrentUser()).get();
		Game game=gameService.createGame(player);
		return "redirect:/board/"+game.getId();
	}


	// @GetMapping(value = "/game/connect/{gameId}")
	// public String connectToGame(@PathVariable("gameId") Integer gameId) {
	// 	// Hasta que no tengamos currentUser conectamos a un user random
	// 	UserDwarf player= userDwarfService.findUserDwarfByUsername2(1);
	// 	gameService.connectToGame(player, gameId);
	// 	return "redirect:/board/{gameId}";
	// }


	public class test{
		public String viewName;
	}
	
	@PostMapping(value="/api/game/{gameId}", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String mainLoop(@PathVariable("gameId") Integer gameId, @RequestBody test test){

		System.out.println("***********"+test.viewName);

		return "board/"+gameId;
	}

	@GetMapping(value = "/game/{gameId}/surrender")
	public String surrender(@PathVariable("gameId") Integer gameId) {
		UserDwarf player= userDwarfService.findUserDwarfByUsername2(CurrentUser.getCurrentUser()).get();
		gameService.surrender(gameId, player);
		return "redirect:/";
	}

	@GetMapping(value = "/game/check")
	public void checkGames() {
		Map<Integer, Game> games = GameStorage.getInstance().getGames();
		// List<String> gameIds = new ArrayList<>();
		// for (Game game : games.values()) gameIds.add(game.getId().toString());
		// System.out.println(gameIds);
		System.out.println(games);
	}

}
