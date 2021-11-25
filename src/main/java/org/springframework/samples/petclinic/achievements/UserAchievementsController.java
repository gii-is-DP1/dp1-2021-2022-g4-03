package org.springframework.samples.petclinic.achievements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserAchievementsController {

    private final AchievementsService achievementsService;
    private final UserDwarfService userDwarfService;
    private final UserAchievementsService userAchievementsService;

    @Autowired
    public UserAchievementsController(final AchievementsService achievementsService, final UserDwarfService userDwarfService, final UserAchievementsService userAchievementsService){
        this.achievementsService = achievementsService;
        this.userDwarfService = userDwarfService;
        this.userAchievementsService = userAchievementsService;
    }

    // @InitBinder
	// public void setAllowedFields(final WebDataBinder dataBinder) {
	// 	dataBinder.setDisallowedFields("id");
	// }

	// @InitBinder("userDwarf")
	// public void initUserDwarfBinder(final WebDataBinder dataBinder) {
	// 	dataBinder.setDisallowedFields("id");
	// }

	// @GetMapping(value = "/user/new")
	// public String initCreationForm(@PathVariable("ownerId") final int ownerId, final ModelMap model) {
	// 	final Pet pet = new Pet();
	// 	final Owner owner = this.ownerService.findOwnerById(ownerId);
	// 	owner.addPet(pet);
	// 	model.put("pet", pet);
	// 	return PetController.VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	// }
    
}
