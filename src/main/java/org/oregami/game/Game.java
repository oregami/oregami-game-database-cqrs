package org.oregami.game;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.oregami.game.command.AddReleaseGroupCommand;
import org.oregami.game.command.CreateGameCommand;
import org.oregami.game.event.GameCreatedEvent;
import org.oregami.game.event.ReleaseGroupAddedEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sebastian on 03.11.16.
 */
@NoArgsConstructor
@Aggregate
@EqualsAndHashCode
public class Game {


    @AggregateIdentifier
    private String id;

    @AggregateMember
    Set<ReleaseGroup> releaseGroups = new HashSet<>();

    String workingTitle;

    @CommandHandler
    public Game(CreateGameCommand command) {
        AggregateLifecycle.apply(new GameCreatedEvent(command.getId(), command.getGameEntryType(), command.getWorkingTitle()));
    }

    @EventSourcingHandler
    public void in(GameCreatedEvent event) {
        this.id = event.getGameId();
    }

    @CommandHandler
    public String on(AddReleaseGroupCommand command) {
        AggregateLifecycle.apply(new ReleaseGroupAddedEvent(command.getGameId(), command.getReleaseGroupId(), command.getReleaseGroupReason()));
        return command.getReleaseGroupId();
    }

    @EventSourcingHandler
    public void in(ReleaseGroupAddedEvent event) {
        ReleaseGroup rg = new ReleaseGroup(event.getReleaseGroupId(), event.getReleaseGroupReason());
        this.releaseGroups.add(rg);
    }



}
