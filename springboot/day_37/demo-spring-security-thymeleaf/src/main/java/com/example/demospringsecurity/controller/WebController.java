package com.example.demospringsecurity.controller;

import com.example.demospringsecurity.security.IAuthenticationFacade;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class WebController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @Secured("ROLE_USER")
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/info-1")
    public ResponseEntity<?> info1() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/info-2")
    public ResponseEntity<?> info2(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/info-3")
    public ResponseEntity<?> info3(Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/info-4")
    public ResponseEntity<?> info4(HttpServletRequest request) {
        return ResponseEntity.ok(request.getUserPrincipal());
    }

    @GetMapping("/info-5")
    public ResponseEntity<?> info5() {
        return ResponseEntity.ok(authenticationFacade.getAuthentication());
    }
}
