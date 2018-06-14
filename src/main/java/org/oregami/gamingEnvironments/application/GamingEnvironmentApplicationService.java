package org.oregami.gamingEnvironments.application;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.oregami.gamingEnvironments.command.AddTitleCommand;
import org.oregami.gamingEnvironments.command.AddTitleUsageCommand;
import org.oregami.gamingEnvironments.command.CreateGamingEnvironmentCommand;
import org.oregami.gamingEnvironments.model.Region;
import org.oregami.transliteratedString.model.TransliteratedStringRepository;
import org.oregami.transliteratedString.readmodel.live.TransliteratedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 14.02.17.
 */
@Service
public class GamingEnvironmentApplicationService {

    private CommandGateway commandGateway;

    private TransliteratedStringRepository transliteratedStringRepository;

    @Autowired
    public GamingEnvironmentApplicationService(CommandGateway commandGateway, TransliteratedStringRepository transliteratedStringRepository) {
        this.commandGateway = commandGateway;
        this.transliteratedStringRepository = transliteratedStringRepository;
    }

    public CompletableFuture<Object> createNewGamingEnvironment(String id, String workingTitle) {
        return commandGateway.send(new CreateGamingEnvironmentCommand(id, workingTitle));
    }


    public CompletableFuture<Object> addTitle(String gamingEnvironmentId, String transliteratedStringId) {
        TransliteratedString transliteratedString = transliteratedStringRepository.findOne(transliteratedStringId);
        return commandGateway.send(new AddTitleCommand(gamingEnvironmentId, transliteratedStringId, transliteratedString.getText()));
    }


    public CompletableFuture<Object> addTitleUsage(String gamingEnvironmentId, String transliteratedStringId, Region region) {
        AddTitleUsageCommand c = new AddTitleUsageCommand(gamingEnvironmentId, transliteratedStringId, region);
        return commandGateway.send(c);
    }








}
