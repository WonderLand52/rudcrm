package com.rudenkoinc.crm.app.controller;

import com.rudenkoinc.crm.app.model.User;
import com.rudenkoinc.crm.app.service.UserService;
import com.rudenkoinc.crm.app.shared.dto.NewUserDTO;
import com.rudenkoinc.crm.app.shared.dto.UserInfoDTO;
//import org.apache.log4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Illia Rudenko
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public UserInfoDTO getUserInfo(Principal principal) {

        User user = userService.findUserByEmail(principal.getName());

        return user != null ? new UserInfoDTO(user.getFirstName(), user.getSecondName()) : null;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody NewUserDTO user) {
        userService.createUser(user.getFirstName(), user.getSecondName(), user.getEmail(), user.getPlainTextPassword());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
