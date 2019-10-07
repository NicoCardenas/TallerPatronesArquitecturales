package com.escuelaing.arep.awsgatewaylambda.persistence;

import java.util.List;

import com.escuelaing.arep.awsgatewaylambda.Entities.User;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 */
@EnableScan
public interface UserRepository  extends CrudRepository<User, String> {

    List<User> getUsersList();

    User createUser(User user);
}