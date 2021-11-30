package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.samples.petclinic.userDwarf.UserDwarf;
import org.springframework.samples.petclinic.userDwarf.UserDwarfService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrentUserController {

    @Autowired
    UserDwarfService userDwarfService;


    @GetMapping(value = "/**/currentuser")
    public UserDwarf showCurrentUser(View currentView) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (!authentication.getAuthorities().stream().map(auth -> auth.getAuthority())
                    .anyMatch(role -> role.equals("ROLE_ANONYMOUS"))) {
                User user= (User) authentication.getPrincipal();
                UserDwarf userDwarf= userDwarfService.findUserDwarfByUsername2(user.getUsername()).get();
                return userDwarf;
            } else {
                System.out.println("User not authenticated");
            }
        }

        return null;
    }

}
