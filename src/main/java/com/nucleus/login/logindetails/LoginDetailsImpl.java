package com.nucleus.login.logindetails;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * LoginDetailsImpl class acts as a service to return details of user.
 *
 * @author  Manish Kumar Shukla
 * @version 1.0
 * @since   2020-12-25
 */
@Service
public class LoginDetailsImpl {

    /**
     * Function to get the username of the User currently logged In.
     * @return the username
     */
    public String getUserName()
    {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
