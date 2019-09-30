package com.escuelaing.arep.awsgatewaylambda.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import com.escuelaing.arep.awsgatewaylambda.Entities.User;
import com.escuelaing.arep.awsgatewaylambda.persistence.userPersistence;

import org.springframework.stereotype.Service;

/**
 * userServiceImpl
 */
@Service
public class userPersistenceImpl implements userPersistence {
    
    private List<User> staticUsers = new ArrayList<User>();

    @Override
    public List<User> getUsersList() {
        if (staticUsers.isEmpty())
            this.getStaticUsers();
        return this.staticUsers;
    }

    @Override
    public User createUser(User user) {
        return addStaticUser(user);
    }

    private User addStaticUser(User user) {
        staticUsers.add(user);
        return staticUsers.get(staticUsers.size() - 1);
    }

    private List<User> getStaticUsers() {
        User n1 = new User();
        User n2 = new User();
        User n3 = new User();

        n1.setName("Mark");
        n2.setName("Jacob");
        n3.setName("Larry");

        n1.setLastname("Otto");
        n2.setLastname("Thornton");
        n3.setLastname("the Bird");

        this.staticUsers.add(n1);
        this.staticUsers.add(n2);
        this.staticUsers.add(n3);

        return this.staticUsers;
    }
}