package com.dhsanchesp.userapi.command.user;

import com.dhsanchesp.userapi.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetUserCommand extends Command {
    final String id;
}
