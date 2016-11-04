package org.oregami.game;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.oregami.api.ChangeNameCommand;
import org.oregami.api.CreateGameCommand;
import org.oregami.api.GameCreatedEvent;
import org.oregami.api.NameChangedEvent;

/**
 * Created by sebastian on 03.11.16.
 */
@NoArgsConstructor
@Aggregate
public class Game {


    @AggregateIdentifier
    private String id;

    private String name;

    @CommandHandler
    public Game(CreateGameCommand command) {
        AggregateLifecycle.apply(new GameCreatedEvent(command.getGameId(), command.getName()));
    }

    @EventSourcingHandler
    public void in (GameCreatedEvent event) {
        this.id = event.getGameId();
    }

    @CommandHandler
    public void handle(ChangeNameCommand command) {
        if (command.getName()==null) {
            throw new RuntimeException("Name must not be null");
        }
        AggregateLifecycle.apply(new NameChangedEvent(command.getGameId(), command.getName()));
    }

    @EventSourcingHandler
    public void in (NameChangedEvent event) {
        this.name = event.getName();
    }
}
