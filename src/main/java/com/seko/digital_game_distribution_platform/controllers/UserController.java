package com.seko.digital_game_distribution_platform.controllers;

import com.seko.digital_game_distribution_platform.models.User;
import com.seko.digital_game_distribution_platform.services.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin ( "http://localhost:4200" )
public class UserController
{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController ( UserService userService, PasswordEncoder passwordEncoder )
    {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping ( "/login" )
    public boolean login ( @RequestBody User user )
    {
        UserDetails userDetails = this.userService.loadUserByUsername ( user.getUsername () );
        return userDetails != null && user.getUsername ()
                .equals ( userDetails.getUsername () ) && passwordEncoder.matches ( user.getPassword (), userDetails.getPassword () );
    }

    @RequestMapping ( "/register" )
    @ResponseBody
    public User register ( @RequestBody User user )
    {
        return this.userService.save ( user );
    }

    @RequestMapping ( "/user" )
    @ResponseBody
    public Principal user ( HttpServletRequest request )
    {
        String authToken = request.getHeader ( "Authorization" )
                .substring ( "Basic".length () )
                .trim ();

        return () -> new String ( Base64.getDecoder ()
                .decode ( authToken ) ).split ( ":" )[ 0 ];
    }

    @GetMapping ( "/user/is/admin" )
    @Secured ( { "ROLE_ADMIN", "ROLE_USER" } )
    public Boolean isAdmin ()
    {
        return this.userService.isAdmin ();
    }

    @GetMapping ( "/user/is/user" )
    @Secured ( { "ROLE_ADMIN", "ROLE_USER" } )
    public Boolean isUser ()
    {
        return this.userService.isUser ();
    }
}
