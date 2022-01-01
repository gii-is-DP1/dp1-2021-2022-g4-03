package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeController {

	@Autowired
	private StatisticsService statisticsService;
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(ModelMap model) {
		  
			
			Long tgp = this.statisticsService.getAllGamesPlayed();
			Long tgw = this.statisticsService.getAllGamesWon();
			Long ti = this.statisticsService.getAllIron();
			Long to = this.statisticsService.getAllGold();
			Long ts = this.statisticsService.getAllSteel();
			Long tob = this.statisticsService.getAllObject();
			Long tm = this.statisticsService.getAllMedal();
			Long timePlayed = this.statisticsService.getAllTimePlayed();
			String ttp = String.format("%dh:%02dm:%02ds", timePlayed / 3600, (timePlayed % 3600) / 60, timePlayed % 60);
			List<String> Urank = this.statisticsService.getRank();
			List<Integer> GWRank = this.statisticsService.getRankGW();

			String p1 = Urank.get(0); Integer p1wg = GWRank.get(0);
			String p2 = Urank.get(1); Integer p2wg = GWRank.get(1);
			String p3 = Urank.get(2); Integer p3wg = GWRank.get(2);

			model.addAttribute("tgp",tgp);
			model.addAttribute("tgw",tgw);
			model.addAttribute("ti",ti);
			model.addAttribute("to",to);
			model.addAttribute("ts",ts);
			model.addAttribute("tob",tob);
			model.addAttribute("tm",tm);
			model.addAttribute("ttp",ttp);
			model.addAttribute("p1",p1);
			model.addAttribute("p1wg",p1wg);
			model.addAttribute("p2",p2);
			model.addAttribute("p2wg",p2wg);
			model.addAttribute("p3",p3);
			model.addAttribute("p3wg",p3wg);

	    return "welcome";
	  }

	 
        
      
    
}
