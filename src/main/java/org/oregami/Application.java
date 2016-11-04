package org.oregami;

import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.oregami.api.ChangeNameCommand;
import org.oregami.api.CreateGameCommand;
import org.oregami.game.Game;

/**
 * Created by sebastian on 03.11.16.
 */
public class Application {

    public static void main(String[] args) {
        Configuration config = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(Game.class)
                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
                .buildConfiguration();

        config.start();

        config.commandBus().dispatch(GenericCommandMessage.asCommandMessage(new CreateGameCommand("42", "Tetris")));
        config.commandBus().dispatch(GenericCommandMessage.asCommandMessage(new ChangeNameCommand("42", "Tetris 2")));
        config.commandBus().dispatch(GenericCommandMessage.asCommandMessage(new ChangeNameCommand("42", null)));
    }


}
