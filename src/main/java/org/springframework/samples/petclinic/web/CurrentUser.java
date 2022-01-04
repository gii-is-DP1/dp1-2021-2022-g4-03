package org.springframework.samples.petclinic.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class CurrentUser {

    public static String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (!authentication.getAuthorities().stream().map(auth -> auth.getAuthority())
                    .anyMatch(role -> role.equals("ROLE_ANONYMOUS"))) {
                User user= (User) authentication.getPrincipal();
                return user.getUsername();
            } else {
                System.out.println("User not authenticated");
            }
        }

        return null;
    }

}
