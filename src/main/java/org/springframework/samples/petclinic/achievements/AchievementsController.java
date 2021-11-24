package org.springframework.samples.petclinic.achievements;

import java.util.Collection;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/achievements/{achievementsId}")
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
		UserDwarf user = this.userDwarfService.getUserSession();
		if(this.userDwarfService.userHaveRol(user.getUsername(), "admin")){
			Achievements achievement = new Achievements();
			model.put("achievements", achievement);
			return AchievementsController.VIEWS_CREATE_OR_UPDATE_ACHIEVEMENTS_FORM;
		}else {
			return "redirect:/";
		}
	}

    @PostMapping(value = "/achievements/new")
	public String processCreationForm(@Valid Achievements achievement, BindingResult result) {
		if (result.hasErrors()) {
			return AchievementsController.VIEWS_CREATE_OR_UPDATE_ACHIEVEMENTS_FORM;
		}
		else {
			//creating achievement
			this.achievementsService.saveAchievement(achievement);
			
			return "redirect:/achievement/" + achievement.getId();
		}
	}

    @GetMapping(value="achievements/delete/{achievementsId}")
	public String deleteAchievements(@PathVariable("achievementsId") int achievementsId,ModelMap modelMap) {
		UserDwarf user = this.userDwarfService.getUserSession();
		if(this.userDwarfService.userHaveRol(user.getUsername(), "admin")){
			Achievements achievement= this.achievementsService.findAchievementById(achievementsId);
			try {
				this.achievementsService.delete(achievement);
				modelMap.addAttribute("message", "¡Logro correctamente eliminado!");
					
			}catch(DataAccessException exception) {
					
					modelMap.addAttribute("message", "El logro no pudo ser eliminado");
			}
				
			return this.initFindForm(modelMap);
		}else {
			return "redirect:/";
		}
	}

    @GetMapping(value = "/achievements/find")
	public String initFindForm(Map<String, Object> model) {
		UserDwarf user = this.userDwarfService.getUserSession();
		if(this.userDwarfService.userHaveRol(user.getUsername(), "admin")){
			model.put("achievements", new Achievements());
			return "achievements/findAchievements";
		}else {
			return "redirect:/";
		}
	}

    @GetMapping(value = "/achievements")
	public String processFindForm(Achievements achievements, BindingResult result, Map<String, Object> model) {
		UserDwarf user = this.userDwarfService.getUserSession();
		if(this.userDwarfService.userHaveRol(user.getUsername(), "admin")){
			// allow parameterless GET request for /achievement to return all records
			if (achievements.getCondition() == null) {
				achievements.setCondition(""); // empty string signifies broadest possible search
			}
	
			// find achievement by condition
			Collection<Achievements> results = this.achievementsService.findAchievementsByCondition(achievements.getCondition());
			if (results.isEmpty()) {
				// no achievement found
				result.rejectValue("condition", "notFound", "not found");
				return "achievements/findAchievements";
			}
			else if (results.size() == 1) {
				// 1 achievement found
				achievements = results.iterator().next();
				return "redirect:/achievements/" + achievements.getId();
			}
			else {
				// multiple achievements found
				model.put("selections", results);
				return "achievements/achievementsList";
			}
		}else {
			return "redirect:/";
		}
	}

    @GetMapping(value = "/achievements/{achievementsId}/edit")
	public String initUpdateAchievementForm(@PathVariable("achievementsId") int achievementId, Model model) {
		UserDwarf user = this.userDwarfService.getUserSession();
		if(this.userDwarfService.userHaveRol(user.getUsername(), "admin")){
			Achievements achievement = this.achievementsService.findAchievementById(achievementId);
			model.addAttribute(achievement);
			return AchievementsController.VIEWS_CREATE_OR_UPDATE_ACHIEVEMENTS_FORM;
		}else {
			return "redirect:/";
		}
	}

    @PostMapping(value = "/achievements/{achievementsId}/edit")
	public String processUpdateAchievementForm(@Valid Achievements achievements, BindingResult result,
			@PathVariable("achievementsId") int achievementsId) {
                achievements.setId(achievementsId);
			this.achievementsService.saveAchievement(achievements);
			return "redirect:/achievements/{achievementsId}";
	}

    @GetMapping("/achievements/{achievementsId}")
	public ModelAndView showOwner(@PathVariable("achievementsId") int achievementsId) {
		UserDwarf user = this.userDwarfService.getUserSession();
		if(this.userDwarfService.userHaveRol(user.getUsername(), "admin")){
			ModelAndView mav = new ModelAndView("achievements/achievementsDetails");
			mav.addObject(this.achievementsService.findAchievementById(achievementsId));
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("redirect:/");
			return mav;
		}
			
		
	}


    
}
