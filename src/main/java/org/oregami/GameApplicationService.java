package org.oregami;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 14.02.17.
 */
@Service
public class GameApplicationService {

    @Autowired
    private CommandGateway commandGateway;

    public CompletableFuture<Object> createNewGame(String gameId, String gameEntryType) {
        return commandGateway.send(new CreateGameCommand(gameId, GameEntryType.valueOf(gameEntryType)));
        /*
        String releaseGroupId = UUID.randomUUID().toString();
        commandBus.dispatch(GenericCommandMessage.asCommandMessage(new AddReleaseGroupCommand(gameId, releaseGroupId, ReleaseGroupReason.ORIGINAL)));
        */
    }

    public CompletableFuture<Object> addReleaseGroup(String gameId, String releaseGroupId, String releaseGroupReason) {
        return commandGateway.send((new AddReleaseGroupCommand(gameId, releaseGroupId, ReleaseGroupReason.valueOf(releaseGroupReason))));
    }






}
