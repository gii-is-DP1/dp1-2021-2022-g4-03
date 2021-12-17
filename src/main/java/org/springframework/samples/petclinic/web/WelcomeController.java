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
		  
			List<Person> persons = new ArrayList<Person>();

			Person person1 = new Person();
			Person person2 = new Person();
			Person person3 = new Person();
			Person person4 = new Person();
			Person person5 = new Person();
			Person person6 = new Person();

			person1.setFirstName("Rafael Ángel");
			person1.setLastName(" Jiménez Fernández");
			persons.add(person1);

			person2.setFirstName("Sergio");
			person2.setLastName(" Rivas Roa");
			persons.add(person2);

			person3.setFirstName("Antonio José");
			person3.setLastName(" Suarez García");
			persons.add(person3);

			person4.setFirstName("Francisco");
			person4.setLastName(" Botello Romero");
			persons.add(person4);

			person5.setFirstName("Fausto");
			person5.setLastName(" Vazquez Rodriguez");
			persons.add(person5);

			person6.setFirstName("Pablo");
			person6.setLastName(" Quindos de la Riva");
			persons.add(person6);

			model.put("persons", persons);
			model.put("title", "G4-03");
			model.put("group", "Developers");

			Long tgp = this.statisticsService.getAllGamesPlayed();
			Long tgw = this.statisticsService.getAllGamesWon();
			Long ti = this.statisticsService.getAllIron();
			Long to = this.statisticsService.getAllGold();
			Long ts = this.statisticsService.getAllSteel();
			Long tob = this.statisticsService.getAllObject();
			Long tm = this.statisticsService.getAllMedal();
			Long timePlayed = this.statisticsService.getAllTimePlayed();
			String ttp = String.format("%dh:%02dm:%02ds", timePlayed / 3600, (timePlayed % 3600) / 60, timePlayed % 60);

			model.addAttribute("tgp",tgp);
			model.addAttribute("tgw",tgw);
			model.addAttribute("ti",ti);
			model.addAttribute("to",to);
			model.addAttribute("ts",ts);
			model.addAttribute("tob",tob);
			model.addAttribute("tm",tm);
			model.addAttribute("ttp",ttp);
			

	    return "welcome";
	  }

	 
        
      
    
}
