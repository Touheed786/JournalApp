package org.touheed.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.touheed.journalApp.entity.User;
import org.touheed.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        try{
            return ResponseEntity.ok(userService.saveUser(user));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
