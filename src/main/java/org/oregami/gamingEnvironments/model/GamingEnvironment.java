package org.oregami.gamingEnvironments.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.oregami.gamingEnvironments.command.AddTitleCommand;
import org.oregami.gamingEnvironments.command.AddTitleUsageCommand;
import org.oregami.gamingEnvironments.command.CreateGamingEnvironmentCommand;
import org.oregami.gamingEnvironments.event.GamingEnvironmentCreatedEvent;
import org.oregami.gamingEnvironments.event.TitleAddedEvent;
import org.oregami.gamingEnvironments.event.TitleUsageAddedEvent;
import org.springframework.core.OrderComparator;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

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
    Set<Title> gametitles = new TreeSet<>(new OrderComparator());

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
        AggregateLifecycle.apply(new TitleAddedEvent(command.getNewId(), command.getGamingEnvironmentId(), command.getTransliteratedStringId()));
        return command.getNewId();
    }

    @EventSourcingHandler
    public void in(TitleAddedEvent event) {
        Title t = new Title(event.getGamingEnvironmentId(), event.getTransliteratedStringId());
        this.gametitles.add(t);
    }

    @CommandHandler
    public String on(AddTitleUsageCommand command) {

        AggregateLifecycle.apply(new TitleUsageAddedEvent(
                command.getGamingEnvironmentId(), //TODO woher die richtige neue ID nehmen?
                command.getTitleId(), command.getRegion()));
        return command.getNewId();
    }

    @EventSourcingHandler
    public void in(TitleUsageAddedEvent event) {
        for (Title t: this.gametitles) {
            if (t.getTitleId().equals(event.getTitleId())) {
                String newId = UUID.randomUUID().toString();
                System.out.println("newId for TitleUsage: " + newId);
                t.getTitleUsages().add(new TitleUsage(newId, event.getRegion()));
            }
        }

    }


}
