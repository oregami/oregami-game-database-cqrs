package org.oregami.transliteratedString.application;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.oregami.gamingEnvironments.command.CreateGamingEnvironmentCommand;
import org.oregami.transliteratedString.Language;
import org.oregami.transliteratedString.Script;
import org.oregami.transliteratedString.command.CreateTransliteratedStringCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 14.02.17.
 */
@Service
public class TransliteratedStringApplicationService {

    /**
     *
     */
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CommandGateway commandGateway;

    public CompletableFuture<Object> createNewTransliteratedString(String id, String text, String language, String script) {
        return commandGateway.send(new CreateTransliteratedStringCommand(id, text, Language.valueOf(language), Script.valueOf(script)));
    }







}
