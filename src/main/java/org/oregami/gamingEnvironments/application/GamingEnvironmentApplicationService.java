package org.oregami.gamingEnvironments.application;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.oregami.gamingEnvironments.command.AddTitleCommand;
import org.oregami.gamingEnvironments.command.AddTitleUsageCommand;
import org.oregami.gamingEnvironments.command.CreateGamingEnvironmentCommand;
import org.oregami.gamingEnvironments.model.Region;
import org.oregami.gamingEnvironments.model.TitleType;
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

    public CompletableFuture<Object> createNewGamingEnvironment(String newId, String workingTitle) {
        return commandGateway.send(new CreateGamingEnvironmentCommand(newId, workingTitle));
    }


    public CompletableFuture<Object> addTitle(String newTitleId, String gamingEnvironmentId, String transliteratedStringId) {
        TransliteratedString transliteratedString = transliteratedStringRepository.findOne(transliteratedStringId);
        return commandGateway.send(new AddTitleCommand(newTitleId, gamingEnvironmentId, transliteratedStringId, transliteratedString.getText()));
    }


    public CompletableFuture<Object> addTitleUsage(String newTitleUsageId, String gamingEnvironmentId, String titleId, Region region, TitleType titleType) {
        AddTitleUsageCommand c = new AddTitleUsageCommand(newTitleUsageId, gamingEnvironmentId, titleId, region, titleType);
        return commandGateway.send(c);
    }








}
