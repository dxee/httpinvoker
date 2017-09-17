package org.blling.httpinvoker.server.controller;

import org.blling.httpinvoker.server.bean.User;
import org.blling.httpinvoker.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Janita on 2017-03-24 14:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/fetch")
    public User getUser(Long userId){
        return userService.getUserById(userId);
    }
}
