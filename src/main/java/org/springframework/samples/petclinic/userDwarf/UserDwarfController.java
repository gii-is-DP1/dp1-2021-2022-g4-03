package org.springframework.samples.petclinic.userDwarf;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class UserDwarfController {

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
    
}
