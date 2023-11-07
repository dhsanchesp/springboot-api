package com.dhsanchesp.userapi.controller;

import com.dhsanchesp.userapi.command.user.GetUserCommand;
import com.dhsanchesp.userapi.handler.BusHandler;
import com.dhsanchesp.userapi.model.UserAddress;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhsanchesp.userapi.dto.UserDto;
import com.dhsanchesp.userapi.repository.UserRepository;

import jakarta.websocket.server.PathParam;

import static org.springframework.http.ResponseEntity.ok;

@AllArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final BusHandler busHandler;
    private UserRepository userRepository;
    
    @GetMapping()
    public UserDto getUser() {
        return new UserDto("123ABC", "Luke", "Skywalker");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "userId") final String userId) {

        final UserDto response = busHandler.request(new GetUserCommand(userId));
        return ok(response);
    }
}
