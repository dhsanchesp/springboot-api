package com.dhsanchesp.userapi.handler.user;

import com.dhsanchesp.userapi.command.user.GetUserCommand;
import com.dhsanchesp.userapi.model.User;
import com.dhsanchesp.userapi.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserHandlerTest {

    @InjectMocks
    private GetUserHandler handler;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Should Handle Get User by ID with success")
    void shouldHandleGetUserByIdWithSuccess() {
        // arrange
        final var expectedUserId = "ABC123";
        final var user = new User("ABC123", "Michael", "Douglas");
        final var command = new GetUserCommand(expectedUserId);
        when(this.userRepository.getUserById(expectedUserId))
                .thenReturn(user);

        //act
        final var response = handler.handle(command);

        //assert
        assertEquals(user.id(), response.getId());
        assertEquals(user.firstName(), response.getFirstName());
        assertEquals(user.lastName(), response.getLastName());
    }

    @Test
    @DisplayName("Should Validate Get User by ID with success")
    void shouldValidateGetUserByIdWithSuccess() {
        // arrange
        final var expectedUserId = "ABC123";
        final var command = new GetUserCommand(expectedUserId);

        //act & assert
        assertDoesNotThrow(() -> handler.validate(command));
    }

    @Test
    @DisplayName("Should Validate Get User by ID throw Exception when ID is invalid")
    void shouldValidateGetUserByIdThrowExceptionWhenUserIdIsInvalid() {
        // arrange
        final var command = new GetUserCommand("");

        //act & assert
        assertThrows(IllegalArgumentException.class,
                () -> handler.validate(command));
    }


}