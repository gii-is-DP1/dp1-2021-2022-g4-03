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
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/usersDwarf/list")
    public String UserDwarfList(ModelMap modelMap){
        
        String view = "/usersDwarf/userDwarfList";
        Iterable<UserDwarf> usersDwarf = userDwarfService.findAll();
        modelMap.addAttribute("usersDwarf",usersDwarf); 
        
        return view;

    }

    @GetMapping(value = "/usersDwarf/new")
	public String initCreationForm(Map<String, Object> model) {
		UserDwarf user = new UserDwarf();
		model.put("userDwarf", user);
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
	public String processFindForm(@RequestParam("username") String username) {

		System.out.println(username + "********************") ;

		if(username==null){
			username =("");
		}
		Collection<UserDwarf> results = this.userDwarfService.findUserByUsername(username);
		System.out.println(results.size());
		if (results.isEmpty()) {
			return "redirect:/usersDwarf/list";
		}
		else{
			UserDwarf user = results.iterator().next();
			return "redirect:/usersDwarf/" + user.getId();
		}
		
	}

    @GetMapping("/usersDwarf/{userId}")
	public ModelAndView showUser(@PathVariable("userId") int userId) {
		ModelAndView mav = new ModelAndView("usersDwarf/userDetails");
		mav.addObject("userDwarf",this.userDwarfService.findById(userId));
		return mav;
	}

    @GetMapping(value = "/usersDwarf/{userId}/edit")
	public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
		UserDwarf user = this.userDwarfService.findById(userId);
		model.addAttribute("userDwarf", user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(value = "/usersDwarf/{userId}/edit")
	public String processUpdateUserForm(UserDwarf user, BindingResult result,
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
