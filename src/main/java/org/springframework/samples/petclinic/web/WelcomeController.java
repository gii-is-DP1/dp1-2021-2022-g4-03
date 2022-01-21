package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.val;


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
			Long mediaOro = this.statisticsService.getMediaOro();
			Long mediaHierro = this.statisticsService.getMediaHierro();
			Long mediaAcero = this.statisticsService.getMediaAcero();
			Long mediaObject = this.statisticsService.getMediaObjetos();
			Long mediaMedallas = this.statisticsService.getMediaMedallas();
			Long mediaPartida = this.statisticsService.getMediaPartidas();

			Long minGold = this.statisticsService.getMinGold();
			Long minIron = this.statisticsService.getMinIron();
			Long minSteel = this.statisticsService.getMinSteel();
			Long minObject = this.statisticsService.getMinObject();

			Long maxGold = this.statisticsService.getMaxGold();
			Long maxIron = this.statisticsService.getMaxIron();
			Long maxSteel = this.statisticsService.getMaxSteel();
			Long maxObject = this.statisticsService.getMaxObject();
			Long maxTime = this.statisticsService.getMaxTimePlayed();
			String ttpMax = String.format("%dh:%02dm:%02ds", maxTime / 3600, (maxTime % 3600) / 60, maxTime % 60);


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
			
			model.addAttribute("mediaOro",mediaOro);
			model.addAttribute("mediaHierro",mediaHierro);
			model.addAttribute("mediaAcero",mediaAcero);
			model.addAttribute("mediaObject",mediaObject);
			model.addAttribute("mediaMedallas",mediaMedallas);
			model.addAttribute("mediaPartida",mediaPartida);

			model.addAttribute("minGold",minGold);
			model.addAttribute("minIron",minIron);
			model.addAttribute("minSteel",minSteel);
			model.addAttribute("minObject",minObject);

			model.addAttribute("maxGold",maxGold);
			model.addAttribute("maxIron",maxIron);
			model.addAttribute("maxSteel",maxSteel);
			model.addAttribute("maxObject",maxObject);
			model.addAttribute("ttpMax",ttpMax);
			

	    return "welcome";
	  }

	 
        
      
    
}
