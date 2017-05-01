package org.oregami.gamingEnvironments;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.oregami.game.ReleaseGroup;
import org.oregami.game.command.AddReleaseGroupCommand;
import org.oregami.game.command.CreateGameCommand;
import org.oregami.game.event.GameCreatedEvent;
import org.oregami.game.event.ReleaseGroupAddedEvent;
import org.oregami.gamingEnvironments.command.AddTitleCommand;
import org.oregami.gamingEnvironments.command.CreateGamingEnvironmentCommand;
import org.oregami.gamingEnvironments.event.GamingEnvironmentCreatedEvent;
import org.oregami.gamingEnvironments.event.TitleAddedEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sebastian on 03.11.16.
 */
@NoArgsConstructor
@Aggregate
@EqualsAndHashCode
public class GamingEnvironment {


    @AggregateIdentifier
    private String id;

    String workingTitle;

    @AggregateMember
    Set<Title> gametitles = new HashSet<>();

    @CommandHandler
    public GamingEnvironment(CreateGamingEnvironmentCommand command) {
        AggregateLifecycle.apply(new GamingEnvironmentCreatedEvent(command.getId(), command.getWorkingTitle()));
    }

    @EventSourcingHandler
    public void in(GamingEnvironmentCreatedEvent event) {
        this.id = event.getId();
    }

    @CommandHandler
    public String on(AddTitleCommand command) {
        AggregateLifecycle.apply(new TitleAddedEvent(command.getGamingEnvironmentId(), command.getRegionId(), command.getTransliteratedStringId()));
        return command.getGamingEnvironmentId();
    }

    @EventSourcingHandler
    public void in(TitleAddedEvent event) {
        Title t = new Title(event.getGamingEnvironmentId(), event.getRegionId(), event.getTransliteratedStringId());
        this.gametitles.add(t);
    }

}
