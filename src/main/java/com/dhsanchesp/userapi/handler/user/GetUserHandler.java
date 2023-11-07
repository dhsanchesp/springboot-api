package com.dhsanchesp.userapi.handler.user;

import com.dhsanchesp.userapi.command.user.GetUserCommand;
import com.dhsanchesp.userapi.dto.UserDto;
import com.dhsanchesp.userapi.handler.RequestHandler;
import com.dhsanchesp.userapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetUserHandler extends RequestHandler<GetUserCommand, UserDto> {

    private final UserRepository userRepository;

    @Override
    protected void validate(final GetUserCommand command) {
        if (StringUtils.isBlank(command.getId())) {
            throw new IllegalArgumentException("User ID must not be null");
        }
    }

    @Override
    protected UserDto handle(GetUserCommand command) {
        final var user = this.userRepository.getUserById(command.getId());
        return new UserDto(user.id(), user.firstName(), user.lastName());
    }
}
