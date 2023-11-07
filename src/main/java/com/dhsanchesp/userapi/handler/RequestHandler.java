package com.dhsanchesp.userapi.handler;

import com.dhsanchesp.userapi.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RequestHandler<C extends Command, R> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected void validate(C command) {}

    public R execute(final C command) {
        try {
            validate(command);
            return handle(command);
        } catch (Exception ex) {
            logger.error("Handler validation failed!");
            throw ex;
        }
    }

    protected abstract R handle(C command);
}
