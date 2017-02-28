package org.oregami.gamingEnvironments.application;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.oregami.game.GameEntryType;
import org.oregami.game.ReleaseGroupReason;
import org.oregami.game.command.AddReleaseGroupCommand;
import org.oregami.game.command.CreateGameCommand;
import org.oregami.gamingEnvironments.command.CreateGamingEnvironmentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 14.02.17.
 */
@Service
public class GamingEnvironmentApplicationService {

    /**
     *
     */
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CommandGateway commandGateway;

    public CompletableFuture<Object> createNewGamingEnvironment(String id, String workingTitle) {
        return commandGateway.send(new CreateGamingEnvironmentCommand(id, workingTitle));
    }







}
