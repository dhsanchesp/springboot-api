package com.dhsanchesp.userapi.handler;

import com.dhsanchesp.userapi.command.Command;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
public class BusHandler {

    private final Map<Class<? extends Command>, RequestHandler<? extends Command, ?>> requestHandlerMap;

    public BusHandler(final List<? extends RequestHandler<? extends Command, ?>> requestHandlers) {
        this.requestHandlerMap = new HashMap<>();
        initializeRequestHandlerMap(requestHandlers);
    }

    public <C extends Command, R> R request(final C command) {
        final var handler = (RequestHandler<C, R>) requestHandlerMap.get(command.getClass());
        return handler.execute(command);
    }

    private void initializeRequestHandlerMap(final List<? extends RequestHandler<? extends Command, ?>> requestHandlers) {
        requestHandlers.forEach(requestHandler -> {
            final var generics = GenericTypeResolver
                    .resolveTypeArguments(requestHandler.getClass(), RequestHandler.class);
            Optional.ofNullable(generics)
                    .flatMap(genericTypes -> Arrays.stream(generics).findFirst())
                    .ifPresent(type -> {
                        final var commandType = (Class<Command>) type;
                        this.requestHandlerMap.put(commandType, requestHandler);
                    });
        });
    }
}
