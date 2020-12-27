package com.nucleus.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * LoginController class acts as a Controller layer for all Security related operations.
 *
 * @author  Manish Kumar Shukla
 * @version 1.0
 * @since   2020-12-25
 */
@Controller
public class LoginController {

    /**
     * Handles incoming GET request to show the login page
     * @return Returns the login jsp page as reponse.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/login/login");
        return mv;
    }

    /**
     * Handles incoming GET request to show the home page after login success
     * @return Returns the home jsp page as reponse.
     */
    @RequestMapping(value = "/loginsuccess", method = RequestMethod.POST)
    public ModelAndView loginPageSuccess() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

    /**
     * Handle the logout functionalities inside the Application
     * @param request Receives incoming http request
     * @param response response object
     * @return redirects to logout page with the message
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /**
     * Handles the Access Denied functionalites inside the Application
     * @param model
     * @return Access denied page in case of Unauthorized Access.
     */
    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "views/login/accessDenied";
    }

    /**
     * Function to get the username of the User currently logged In.
     * @return the username
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

//    public String getRole()
//    {
//        Collection<? extends GrantedAuthority> userRoles = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        userRoles = ((UserDetails)principal).getAuthorities();
//        String s = "";
//        for (GrantedAuthority role : userRoles) {
//            s = s+role.getAuthority();
//        }
//        return s;
//    }
}
