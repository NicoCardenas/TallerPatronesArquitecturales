package com.escuelaing.arep.awsgatewaylambda.persistence;

import java.util.List;

import com.escuelaing.arep.awsgatewaylambda.Entities.User;

/**
 * userService
 */
public interface userPersistence {

    List<User> getUsersList();

    User createUser(User user);
}