package org.springframework.samples.petclinic.achievements;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AchievementsController {
    
    private static final String VIEWS_CREATE_OR_UPDATE_ACHIEVEMENTS_FORM = "achievements/createOrUpdateAchievementsForm";

    private final AchievementsService achievementsService;
    private final UserDwarfService userDwarfService;

    @Autowired
    public AchievementsController(final AchievementsService achievementService, final UserDwarfService userDwarfService) {
        this.achievementsService = achievementService;
        this.userDwarfService = userDwarfService;
    }

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/achievements/new")
	public String initCreationForm(Map<String, Object> model) {
		Achievements achievements = new Achievements();
		model.put("achievements", achievements);
		return AchievementsController.VIEWS_CREATE_OR_UPDATE_ACHIEVEMENTS_FORM;
	}

    @PostMapping(value = "/achievements/new")
	public String processCreationForm(@Valid Achievements achievements, BindingResult result) {
		if (result.hasErrors()) {
			return AchievementsController.VIEWS_CREATE_OR_UPDATE_ACHIEVEMENTS_FORM;
		}
		else {
			//creating achievement
			this.achievementsService.saveAchievement(achievements);
			
			return "redirect:/achievements/list";
		}
	}

    @GetMapping(value="/achievements/{achievementsId}/delete")
	public String deleteAchievements(@PathVariable("achievementsId") int achievementsId,ModelMap modelMap) {
		
		String view = "achievements/achievementsList";
		Optional<Achievements> achievements = this.achievementsService.findByIdOptional(achievementsId);
		if(achievements.isPresent()){
			achievementsService.delete(achievements.get());
			modelMap.addAttribute("message", "Logro borrado correctamente");
		}else{
			modelMap.addAttribute("message", "Logro no encontrado");
		}
		return "redirect:/achievements/list";
	}

	@GetMapping(value = "achievements/findAchievements")
	public String initFindForm(Map<String, Object> model) {
		model.put("achievements", new Achievements());
		return "achievements/findAchievements";
	}


	@GetMapping(value="/achievements/list")
	public String achievementsList(ModelMap modelMap){
		String view = "achievements/achievementsList";
		Iterable<Achievements> achievements = achievementsService.findAll();
		modelMap.addAttribute("achievements", achievements);
		return view;
	}

    @GetMapping(value = "/achievements")
	public String processFindForm(@RequestParam("condition") String condition) {

		System.out.println(condition + "********************") ;

		if(condition==null){
			condition =("");
		}
		Collection<Achievements> results = this.achievementsService.findAchievementsByCondition(condition);
		System.out.println(results.size() + "+++++++++++++++++++++++++++++++++++++++");
		
		if (results.isEmpty()) {
			return "redirect:/achievements/list";
		}
		else{
			Achievements ach = results.iterator().next();
			return "redirect:/achievements/" + ach.getId();
		}
		
	}

    // @GetMapping(value = "/achievements/{achievementsId}/edit")
	// public String initUpdateAchievementForm(@PathVariable("achievementsId") int achievementId, Model model) {
	// 	UserDwarf user = this.userDwarfService.getUserSession();
	// 	if(this.userDwarfService.userHaveRol(user.getUsername(), "admin")){
	// 		Achievements achievement = this.achievementsService.findAchievementById(achievementId);
	// 		model.addAttribute(achievement);
	// 		return AchievementsController.VIEWS_CREATE_OR_UPDATE_ACHIEVEMENTS_FORM;
	// 	}else {
	// 		return "redirect:/";
	// 	}
	// }

    // @PostMapping(value = "/achievements/{achievementsId}/edit")
	// public String processUpdateAchievementForm(@Valid Achievements achievements, BindingResult result,
	// 		@PathVariable("achievementsId") int achievementsId) {
    //             achievements.setId(achievementsId);
	// 		this.achievementsService.saveAchievement(achievements);
	// 		return "redirect:/achievements/{achievementsId}";
	// }




    
}
