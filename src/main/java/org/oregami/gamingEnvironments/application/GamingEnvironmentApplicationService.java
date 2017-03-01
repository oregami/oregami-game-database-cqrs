package org.oregami.gamingEnvironments.application;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.oregami.common.types.Language;
import org.oregami.common.types.Script;
import org.oregami.gamingEnvironments.command.AddTitleCommand;
import org.oregami.gamingEnvironments.command.CreateGamingEnvironmentCommand;
import org.oregami.regions.RegionId;
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


    public CompletableFuture<Object> addTitle(String gamingEnvironmentId, RegionId regionId, String title, String language, String script) {
        return commandGateway.send(new AddTitleCommand(gamingEnvironmentId, regionId, title, Language.valueOf(language), Script.valueOf(script)));
    }







}
