package org.touheed.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.touheed.journalApp.entity.User;
import org.touheed.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        try{
            return ResponseEntity.ok(userService.updateUser(user));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/getUser/{userName}")
    public ResponseEntity<Object> getUser(@PathVariable("userName") String userName){
        try {
            return ResponseEntity.ok(userService.findByUser(userName));
        }catch (Exception e){
            ResponseEntity<Object> ResponseEntity;
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(){
        return userService.deleteUser();
    }
}
