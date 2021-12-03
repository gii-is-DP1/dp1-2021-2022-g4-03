package org.springframework.samples.petclinic.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CurrentUserController {

    @Autowired
    UserDwarfService userDwarfService;

    @ModelAttribute("currentUser")
    @GetMapping(value = "/**/currentuser")
    public UserDwarf showCurrentUser(Model model,HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (!authentication.getAuthorities().stream().map(auth -> auth.getAuthority())
                    .anyMatch(role -> role.equals("ROLE_ANONYMOUS"))) {
                User user= (User) authentication.getPrincipal();
                UserDwarf currentUser= userDwarfService.findUserDwarfByUsername2(user.getUsername()).get();
                response.addHeader("Refresh", "5");
                return currentUser;
            } else {
                System.out.println("User not authenticated");
            }
        }

        return new UserDwarf();
    }

}
