package com.yura.security.controller;

import com.yura.security.entity.User;
import com.yura.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * This method returns view user
     * @param model This parameter use to pass values to view
     * @return String This returns view user
     */
    @RequestMapping(value = "/user")
    public String userPage(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<User> user = userService.getUserByName(userDetails.getUsername());
            model.put("username", user.get(0).getName());
        }
        List<User> users = userService.getAllUsers();
        model.put("users", users);
        return "user";
    }

    /**
     * This method remove user from database
     * @param id This is user id in database
     * @return String This returns view user
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String removeUser(
            @PathVariable(value = "id") int id
    ) {
        userService.removeUser(id);
        return "user";
    }

    /**
     * This method edit user's data
     * @param id This is user id in database
     * @param name This is new user's name
     * @param firstName This is new user's first name
     * @param email This is new user's email
     * @param password This is new user's password
     * @return String This returns view user
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public String editUser(
            @PathVariable(value = "id") int id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password
    ) {
        User user = userService.getUser(id);
        String pass;
        if (user.getPassword().equals(password)) pass = password;
        else pass = RootController.md5Apache(password);
        user.setName(name);
        user.setFirstName(firstName);
        user.setemail(email);
        user.setPassword(pass);

        userService.editUser(user);
        return "user";
    }

    /**
     * This method add user to database
     * @param name This is new user's name
     * @param firstName This is new user's first name
     * @param email This is new user's email
     * @param password This is new user's password
     * @return String This returns view user
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String addUser(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password, ModelMap model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<User> user = userService.getUserByName(userDetails.getUsername());
            model.put("username", user.get(0).getName());
        }
        String pass = RootController.md5Apache(password);
        User user = new User(name, firstName, email, pass);
        System.out.println("this: "+name+" "+firstName+" "+email+" "+password);
        userService.addUser(user);

        List<User> users = userService.getAllUsers();
        model.put("users", users);
        return "user";
    }
}
