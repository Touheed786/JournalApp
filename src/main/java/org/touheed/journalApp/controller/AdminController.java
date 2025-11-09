package org.touheed.journalApp.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.touheed.journalApp.entity.User;
import org.touheed.journalApp.service.UserService;

@RestController()
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    AdminController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<Object> getAllUser(){
        return userService.findAllUser();
    }

    @PostMapping("/adminAdmin")
    public ResponseEntity<Object> getAllUser(@RequestBody() User user){
        return userService.saveAdminUser(user);
    }
}
