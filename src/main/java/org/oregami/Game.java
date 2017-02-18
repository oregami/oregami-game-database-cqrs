package org.oregami;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.*;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sebastian on 03.11.16.
 */
@NoArgsConstructor
@AggregateRoot
@Aggregate
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
