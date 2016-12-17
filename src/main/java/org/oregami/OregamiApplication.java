package org.oregami;

import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.spring.config.EnableAxon;
import org.oregami.game.Game;
import org.oregami.game.GameEntryType;
import org.oregami.game.ReleaseGroupReason;
import org.oregami.game.commands.AddReleaseGroupCommand;
import org.oregami.game.commands.CreateGameCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@EnableAxon
public class OregamiApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(OregamiApplication.class, args);


        Configuration config = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(Game.class)
                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
                .buildConfiguration();

        config.start();

        CommandBus commandBus = config.commandBus();
        String gameId = UUID.randomUUID().toString();
        commandBus.dispatch(GenericCommandMessage.asCommandMessage(new CreateGameCommand(gameId, GameEntryType.GAME)));
        
        String releaseGroupId = UUID.randomUUID().toString();
        commandBus.dispatch(GenericCommandMessage.asCommandMessage(new AddReleaseGroupCommand(gameId, releaseGroupId, ReleaseGroupReason.ORIGINAL)));


    }

}
