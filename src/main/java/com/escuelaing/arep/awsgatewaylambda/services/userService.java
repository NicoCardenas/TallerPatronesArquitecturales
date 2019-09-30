package com.escuelaing.arep.awsgatewaylambda.services;

import com.escuelaing.arep.awsgatewaylambda.Entities.User;
import com.escuelaing.arep.awsgatewaylambda.persistence.userPersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * userService
 */
@Service
public class userService {

    @Autowired
    userPersistence up;

    public Iterable<User> getAllUsers() {
        return up.getUsersList();
    }
    
    public User createUser(User user) {
        return up.createUser(user);
    }
}