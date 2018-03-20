package org.oregami.gamingEnvironments.application;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.oregami.gamingEnvironments.command.AddTitleCommand;
import org.oregami.gamingEnvironments.command.CreateGamingEnvironmentCommand;
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

    /**
     *
     */
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private TransliteratedStringRepository transliteratedStringRepository;

    public CompletableFuture<Object> createNewGamingEnvironment(String id, String workingTitle) {
        return commandGateway.send(new CreateGamingEnvironmentCommand(id, workingTitle));
    }


    public CompletableFuture<Object> addTitle(String gamingEnvironmentId, String transliteratedStringId) {
        TransliteratedString transliteratedString = transliteratedStringRepository.findOne(transliteratedStringId);
        return commandGateway.send(new AddTitleCommand(gamingEnvironmentId, transliteratedStringId, transliteratedString.getText()));
    }







}
