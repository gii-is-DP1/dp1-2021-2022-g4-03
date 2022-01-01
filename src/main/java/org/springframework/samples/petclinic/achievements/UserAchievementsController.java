package org.springframework.samples.petclinic.achievements;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.statistics.Statistics;
import org.springframework.samples.petclinic.statistics.StatisticsService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.samples.petclinic.web.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserAchievementsController {


	@Autowired
	private StatisticsService statisticsService;

	@Autowired
	private AchievementsService achievementsService;

	@Autowired
	private UserAchievementsService userAchievementsService; 


    @InitBinder
	public void setAllowedFields(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("userDwarf")
	public void initUserDwarfBinder(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/profile/achievements")
	public String userDwarfAchievementsProfile(ModelMap modelMap){
		String view = "achievements/achievementsProfile";

		String currentUserUsername= CurrentUser.getCurrentUser();
		Statistics statistic = this.statisticsService.findStatisticsByUsername2(currentUserUsername).get();
		UserAchievements user = this.userAchievementsService.findAchievementsById(1);
		Achievements achievement = this.achievementsService.findAchievementById(1);
		String condition = achievement.getCondition();

		Integer s = condition.indexOf("=");
		Double goalValue = Double.parseDouble(condition.substring(s+1));
		Double gold = Double.valueOf(statistic.getTotalGold());
		
		if(gold==goalValue){
			user.setObtainingDate(LocalDate.now());
			user.setProgress(1.);
		}else if(gold==0){
			user.setProgress(0.);
		}else{
			Double c = (gold/goalValue)*100;
			user.setProgress(c);
		}
		String progress = String.format("%.0f", user.getProgress()) + "%"; 
		
		modelMap.addAttribute("progress",progress);

		return view;
	}
    
}
