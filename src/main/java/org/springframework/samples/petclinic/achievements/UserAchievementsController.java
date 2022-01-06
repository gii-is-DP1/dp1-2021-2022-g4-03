package org.springframework.samples.petclinic.achievements;

import java.lang.reflect.Field;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.statistics.Statistics;
import org.springframework.samples.petclinic.statistics.StatisticsService;
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
	private UserDwarfService userDwarfService;

	@Autowired
	private StatisticsService statisticsService;

	@Autowired
	private AchievementsService achievementsService;

	@Autowired
	private UserAchievementsService userAchievementsService;

    @Autowired
    private CurrentUser currentUser;

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

		String currentUserUsername= currentUser.getCurrentUser();
		Statistics statistic = this.statisticsService.findStatisticsByUsername2(currentUserUsername).get();

		Class<?> c = statistic.getClass();

		Integer numAchievements = this.achievementsService.achievementsCount();

		UserAchievements user = this.userAchievementsService.findUserAchievementsById(1);


		for(int i=1; i<= numAchievements;i++){

			Achievements achievement = this.achievementsService.findAchievementById(i);
			String pic = achievement.getPic();
			String condition = achievement.getCondition();
			String dp = achievement.getDescription();

			Integer s = condition.indexOf("=");

			String atributeValue = condition.substring(0,s);
			Double goalValue = Double.parseDouble(condition.substring(s+1));

			Field f=null;
			try {
				f = c.getDeclaredField(atributeValue);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			Double material=0.;
			try {
				material = Double.valueOf((Integer)f.get(statistic));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			if(material==goalValue || material>goalValue){
				user.setObtainingDate(LocalDate.now());
				user.setProgress(1.);

			}else if(material==0){
				user.setProgress(0.);
			}else{
				Double v = (material/goalValue);
				user.setProgress(v);
			}

			String progress = String.format("%.0f", user.getProgress()*100) + "%";

			modelMap.addAttribute("progress"+i ,progress);
			modelMap.addAttribute("pic"+i ,pic);
			modelMap.addAttribute("dp"+i,dp);

		}
		return view;
	}

	@GetMapping("/profile/playerAchievements/{userDwarfId}")
	public String playerAchievementsProfile(@PathVariable("userDwarfId") int userDwarfId, ModelMap modelMap){
		String view = "achievements/achievementsProfile";

		UserDwarf userDwarf = this.userDwarfService.findUserDwarfById(userDwarfId);
		Statistics statistic = this.statisticsService.findStatisticsByUsername2(userDwarf.getUsername()).get();

		Class<?> c = statistic.getClass();

		Integer numAchievements = this.achievementsService.achievementsCount();

		UserAchievements user = this.userAchievementsService.findUserAchievementsById(1);


		for(int i=1; i<= numAchievements;i++){

			Achievements achievement = this.achievementsService.findAchievementById(i);
			String pic = achievement.getPic();
			String condition = achievement.getCondition();
			String dp = achievement.getDescription();

			Integer s = condition.indexOf("=");

			String atributeValue = condition.substring(0,s);
			Double goalValue = Double.parseDouble(condition.substring(s+1));

			Field f=null;
			try {
				f = c.getDeclaredField(atributeValue);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			Double material=0.;
			try {
				material = Double.valueOf((Integer)f.get(statistic));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			if(material==goalValue || material>goalValue){
				user.setObtainingDate(LocalDate.now());
				user.setProgress(1.);

			}else if(material==0){
				user.setProgress(0.);
			}else{
				Double v = (material/goalValue);
				user.setProgress(v);
			}

			String progress = String.format("%.0f", user.getProgress()*100) + "%";

			modelMap.addAttribute("progress"+i ,progress);
			modelMap.addAttribute("pic"+i ,pic);
			modelMap.addAttribute("dp"+i,dp);

		}
		return view;
	}

}
