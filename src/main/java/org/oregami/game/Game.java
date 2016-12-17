package org.oregami.game;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.oregami.game.commands.AddReleaseGroupCommand;
import org.oregami.game.commands.CreateGameCommand;
import org.oregami.game.events.ReleaseGroupAddedEvent;
import org.oregami.game.events.GameCreatedEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sebastian on 03.11.16.
 */
@NoArgsConstructor
@AggregateRoot
@EqualsAndHashCode
public class Game {


    @AggregateIdentifier
    private String id;


    @AggregateMember
    Set<ReleaseGroup> releaseGroups = new HashSet<>();

    @CommandHandler
    public Game(CreateGameCommand command) {
        AggregateLifecycle.apply(new GameCreatedEvent(command.getId(), command.getGameEntryType()));
    }

    @EventSourcingHandler
    public void in(GameCreatedEvent event) {
        this.id = event.getGameId();
    }

    @CommandHandler
    public void on(AddReleaseGroupCommand command) {
        AggregateLifecycle.apply(new ReleaseGroupAddedEvent(command.getGameId(), command.getReleaseGroupId(), command.getReleaseGroupReason()));
    }

    @EventSourcingHandler
    public void in(ReleaseGroupAddedEvent event) {
        ReleaseGroup rg = new ReleaseGroup(event.getReleaseGroupId(), event.getReleaseGroupReason());
        this.releaseGroups.add(rg);

    }



}
