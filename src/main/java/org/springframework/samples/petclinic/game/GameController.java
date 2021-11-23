package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.BoardService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		gameService.createGame(userDwarfService.findById(3));
		return "/game/1";
	}

	@GetMapping(value = "/game/connect/{gameId}")
	public String connectToGame(@PathVariable("gameId") Integer gameId) {
		// Hasta que no tengamos currentUser conectamos a un user random
		gameService.connectToGame(userDwarfService.findById(1), gameId);
		return "game/1";
	}

	@PostMapping(value = "/game/{gameId}/surrender")
	public String surrender(@PathVariable("gameId") Integer gameId) {
		gameService.surrender(gameId, userDwarfService.findById(1));
		return "redirect:/";
	}

}
