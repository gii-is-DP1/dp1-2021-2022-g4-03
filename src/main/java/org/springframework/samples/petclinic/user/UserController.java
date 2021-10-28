
package org.springframework.samples.petclinic.user;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

	private static final String VIEWS_USER_CREATE_FORM = "users/createOrUpdateUserForm";

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		return VIEWS_USER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_FORM;
		}
		else {
			this.userService.saveUser(user);
			return "redirect:/";
		}
	}

	@GetMapping(value="/users")
	public String viewUsersList(Map<String, Object> model){
		List<User> users= userService.getAllUsers();
        model.put("users", users);
        return "users/userView";
	}

}
