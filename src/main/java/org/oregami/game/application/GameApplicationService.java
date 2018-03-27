package org.oregami.game.application;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.oregami.game.command.AddReleaseGroupCommand;
import org.oregami.game.command.CreateGameCommand;
import org.oregami.game.model.GameEntryType;
import org.oregami.game.model.ReleaseGroupReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 14.02.17.
 */
@Service
public class GameApplicationService {

    private CommandGateway commandGateway;

    @Autowired
    public GameApplicationService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<Object> createNewGame(String gameId, String gameEntryType, String workingTitle) {
        return commandGateway.send(new CreateGameCommand(gameId, GameEntryType.valueOf(gameEntryType), workingTitle));
    }

    public CompletableFuture<Object> addReleaseGroup(String gameId, String releaseGroupId, String releaseGroupReason) {
        return commandGateway.send((new AddReleaseGroupCommand(gameId, releaseGroupId, ReleaseGroupReason.valueOf(releaseGroupReason))));
    }






}
