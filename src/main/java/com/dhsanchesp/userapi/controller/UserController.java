package com.dhsanchesp.userapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhsanchesp.userapi.dto.UserDto;
import com.dhsanchesp.userapi.repository.UserRepository;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class UserController {   

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping()
    public UserDto getUser() {
        return new UserDto("123ABC", "Luke", "Skywalker");
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathParam(value = "id") final String id) {
        final var user = this.userRepository.getUserById(id);

        return new UserDto(user.getId(), user.getFirstName(), user.getLastName());
    }
}
