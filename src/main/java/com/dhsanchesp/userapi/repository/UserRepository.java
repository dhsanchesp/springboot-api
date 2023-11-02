package com.dhsanchesp.userapi.repository;

import org.springframework.stereotype.Repository;

import com.dhsanchesp.userapi.model.User;

@Repository
public class UserRepository {
    
    public User getUserById(final String id) {
        return new User(id, "Luke", "Skywalker");
    }
}
