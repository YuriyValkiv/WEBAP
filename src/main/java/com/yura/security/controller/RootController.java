package com.yura.security.controller;

import com.yura.security.entity.User;
import com.yura.security.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RootController {

    @Autowired
    private UserService userService;

    /**
     * This method returns view index
     * @param modelMap This parameter use to pass values to view
     * @return String This returns view index
     */
    @RequestMapping(value = "/")
    public String getIndex(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<User> user = userService.getUserByName(userDetails.getUsername());
            modelMap.put("username", user.get(0).getName());
        }
        return "index";
    }

    /**
     * This method encode password
     * @param st String parameter. This consist of password which will be encoded
     * @return String encoded password
     */
    public static String md5Apache(String st) {
        return DigestUtils.md5Hex(st);
    }
}
