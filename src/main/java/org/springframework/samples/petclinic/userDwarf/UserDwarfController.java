package org.springframework.samples.petclinic.userDwarf;



import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserDwarfController {

    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "usersDwarf/createOrUpdateUserDwarfForm";

    @Autowired
    private UserDwarfService userDwarfService; 

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @GetMapping("/userDwarf/list")
    public String UserDwarfList(ModelMap modelMap){
        
        String view = "/usersDwarf/userDwarfList";
        Iterable<UserDwarf> usersDwarf = userDwarfService.findAll();
        modelMap.addAttribute("usersDwarf",usersDwarf); 
        
        return view;

    }

    @GetMapping(value = "/usersDwarf/new")
	public String initCreationForm(Map<String, Object> model) {
		UserDwarf user = new UserDwarf();
		model.put("user", user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/usersDwarf/new")
	public String processCreationForm(@Valid UserDwarf user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user, and authority
			this.userDwarfService.saveUser(user);
			return "redirect:/";
		}
	}

    @GetMapping(value = "/usersDwarf/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("user", new UserDwarf());
		return "usersDwarf/findUsers";
	}

    @GetMapping(value = "/usersDwarf")
	public String processFindForm(UserDwarf user, BindingResult result, Map<String, Object> model) {

		// find users by username
		Collection<UserDwarf> results = this.userDwarfService.findUserByUsername(user.getUsername());
		if (results.isEmpty()) {
			// no users found
			result.rejectValue("username", "notFound", "not found");
			return "usersDwarf/findUsers";
		}
		else if (results.size() == 1) {
			// 1 user found
			user = results.iterator().next();
			return "redirect:/usersDwarf/" + user.getId();
		}
		else {
			// multiple owners found
			model.put("usersDwarf", results);
			return "usersDwarf/userDwarfList";
		}
	}

    @GetMapping("/usersDwarf/{userId}")
	public ModelAndView showUser(@PathVariable("userId") int userId) {
		ModelAndView mav = new ModelAndView("usersDwarf/userDetails");
		mav.addObject(this.userDwarfService.findById(userId));
		return mav;
	}

    @GetMapping(value = "/usersDwarf/{userId}/edit")
	public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
		UserDwarf user = this.userDwarfService.findById(userId);
		model.addAttribute(user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(value = "/usersDwarf/{userId}/edit")
	public String processUpdateUserForm(@Valid UserDwarf user, BindingResult result,
			@PathVariable("userId") int userId) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		}
		else {
			user.setId(userId);
			this.userDwarfService.saveUser(user);
			return "redirect:/usersDwarf/{userId}";
		}
	}

    
}
