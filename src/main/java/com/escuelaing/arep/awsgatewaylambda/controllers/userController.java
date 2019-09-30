package com.escuelaing.arep.awsgatewaylambda.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.escuelaing.arep.awsgatewaylambda.Entities.User;
import com.escuelaing.arep.awsgatewaylambda.services.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class userController {

    @Autowired
    userService us;

    // ================= Get ================= //
    @GetMapping("/")
    public ResponseEntity<?> getUsersList() {
        try {
            return new ResponseEntity<>(us.getAllUsers(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found users", HttpStatus.NOT_FOUND);
        }
    }

    // ================= Post ================= //
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(us.createUser(user), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Can't create user", HttpStatus.NOT_FOUND);
        }
    }
    
}