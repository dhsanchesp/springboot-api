package com.dhsanchesp.userapi.controller;

import com.dhsanchesp.userapi.command.user.GetUserCommand;
import com.dhsanchesp.userapi.dto.UserDto;
import com.dhsanchesp.userapi.handler.BusHandler;
import com.dhsanchesp.userapi.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@OpenAPIDefinition(info = @Info(summary = "User Controller"))
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

    @Operation(summary = "Get User by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "userId") final String userId) {

        final UserDto response = busHandler.request(new GetUserCommand(userId));
        return ok(response);
    }
}
