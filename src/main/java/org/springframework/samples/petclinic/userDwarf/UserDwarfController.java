package org.springframework.samples.petclinic.userDwarf;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.achievements.Achievements;
import org.springframework.samples.petclinic.achievements.AchievementsService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.samples.petclinic.web.CurrentUser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Controller
public class UserDwarfController {

	private static final String VIEWS_USERDWARF_CREATE_OR_UPDATE_FORM = "usersDwarf/createOrUpdateUserDwarfForm";

	@Autowired
	private UserDwarfService userDwarfService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	private AchievementsService achievementsService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@Getter
	@Setter
	@EqualsAndHashCode
	public class Wrapper {

		UserDwarf userDwarf;

		List<String> roles = new ArrayList<>();
	}

	@GetMapping("/usersDwarf/list")
	public String UserDwarfList(ModelMap modelMap) {

		String view = "/usersDwarf/userDwarfList";
		Iterable<UserDwarf> usersDwarf = userDwarfService.findAll();
		modelMap.addAttribute("usersDwarf", usersDwarf);

		return view;

	}

	

	@GetMapping(value = "/usersDwarf/register")
	public String initCreationFormRegister(Map<String, Object> model) {
		UserDwarf userDwarf = new UserDwarf();
		Wrapper wrapper = new Wrapper();
		wrapper.setUserDwarf(userDwarf);
		model.put("wrapper", wrapper);
		model.put("registerCheck", true);
		return VIEWS_USERDWARF_CREATE_OR_UPDATE_FORM;
	}

	@GetMapping(value = "/usersDwarf/new")
	public String initCreationForm(Map<String, Object> model) {
		UserDwarf userDwarf = new UserDwarf();
		Wrapper wrapper = new Wrapper();
		wrapper.setUserDwarf(userDwarf);
		model.put("wrapper", wrapper);
		model.put("boolList", List.of("true", "false"));
		return VIEWS_USERDWARF_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = { "/usersDwarf/new", "/usersDwarf/register" })
	public String processCreationForm(Wrapper wrapper, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USERDWARF_CREATE_OR_UPDATE_FORM;
		} else {
			@Valid
			UserDwarf userDwarf = wrapper.userDwarf;
			// creating userDwarf
			this.userDwarfService.saveUserDwarf(userDwarf, wrapper.roles);
			return "redirect:/";
		}
	}

	@GetMapping(value = "/usersDwarf/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("userDwarf", new UserDwarf());
		return "usersDwarf/findUsers";
	}

	@GetMapping(value = "/usersDwarf")
	public String processFindForm(@RequestParam("username") String username) {

		System.out.println(username + "********************");

		if (username == null) {
			username = ("");
		}
		Collection<UserDwarf> results = this.userDwarfService.findUserDwarfByUsername(username);
		System.out.println(results.size());
		if (results.isEmpty()) {
			return "redirect:/usersDwarf/list";
		} else {
			UserDwarf userDwarf = results.iterator().next();
			return "redirect:/usersDwarf/" + userDwarf.getId();
		}

	}
	

	@GetMapping(value ="/profile")
		public String UserDwarfProfile( ModelMap modelMap){
			String view = "usersDwarf/userDwarfProfile";
			Wrapper wrapper = new Wrapper();
			String currentUserUsername= CurrentUser.getCurrentUser();
			UserDwarf userDwarf = this.userDwarfService.findUserDwarfByUsername2(currentUserUsername).get();
		//	Achievements achievements = this.achievementsService
			wrapper.setUserDwarf(userDwarf);
			wrapper.setRoles(authoritiesService.getRolesUserByUsername(userDwarf.getUsername()));
			modelMap.addAttribute("wrapper",wrapper);
			return view;
		}

	@GetMapping("/usersDwarf/{userDwarfId}")
	public ModelAndView showUserDwarf(@PathVariable("userDwarfId") int userDwarfId) {
		ModelAndView mav = new ModelAndView("usersDwarf/userDetails");
		Wrapper wrapper = new Wrapper();
		UserDwarf userDwarf = this.userDwarfService.findById(userDwarfId);
		wrapper.setUserDwarf(userDwarf);
		wrapper.setRoles(authoritiesService.getRolesUserByUsername(userDwarf.getUsername()));
		mav.addObject("wrapper", wrapper);
		return mav;
	}

	@GetMapping(value = "/usersDwarf/{userDwarfId}/edit")
	public String initUpdateUserDwarfForm(@PathVariable("userDwarfId") int userDwarfId, Model model) {
		UserDwarf userDwarf = this.userDwarfService.findById(userDwarfId);
		List<String> roles = authoritiesService.getRolesUserByUsername(userDwarf.getUsername());
		Wrapper wrapper = new Wrapper();
		wrapper.setRoles(roles);
		wrapper.setUserDwarf(userDwarf);
		model.addAttribute("wrapper", wrapper);
		model.addAttribute("boolList", List.of("true", "false"));
		return VIEWS_USERDWARF_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/usersDwarf/{userDwarfId}/edit")
	public String processUpdateUserDwarfForm(Wrapper wrapper, BindingResult result,
			@PathVariable("userDwarfId") int userDwarfId) {
		if (result.hasErrors()) {
			return VIEWS_USERDWARF_CREATE_OR_UPDATE_FORM;
		} else {
			@Valid
			UserDwarf userDwarf = wrapper.userDwarf;
			userDwarf.setId(userDwarfId);
			this.userDwarfService.saveUserDwarf(userDwarf, wrapper.roles);
			return "redirect:/usersDwarf/{userDwarfId}";
		}
	}

	@GetMapping(value = "/usersDwarf/{userDwarfId}/delete")
	public String deleteUserDwarf(@PathVariable("userDwarfId") int userDwarfId, ModelMap modelmap) {
		String view = "usersDwarf/userDwarfList";
		Optional<UserDwarf> userDwarf  = this.userDwarfService.findByIdOptional(userDwarfId);
		if(userDwarf.isPresent()){
			userDwarfService.deleteUserDwarf(userDwarf.get());
			modelmap.addAttribute("message","User successfully deleted");
		}else{
			modelmap.addAttribute("message","User not found");
		}
		return "redirect:/usersDwarf/list";
	}


}
