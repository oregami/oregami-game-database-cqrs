package org.oregami.regions;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.oregami.regions.command.CreateRegionCommand;
import org.oregami.regions.event.RegionCreatedEvent;

@Aggregate
@EqualsAndHashCode
@NoArgsConstructor
public class Region {

    @AggregateIdentifier
    private RegionId name;

	private boolean isCountry;
	private boolean isBusinessRegion;
	private String description;


    @CommandHandler
    public Region(CreateRegionCommand command) {
        AggregateLifecycle.apply(new RegionCreatedEvent(command.getName(), command.isCountry(), command.isBusinessRegion(), command.getDescrition()));
    }

    @EventSourcingHandler
    public void in(RegionCreatedEvent event) {
        this.name = event.getName();
    }

	public RegionId getName() {
		return name;
	}

	public boolean isCountry() {
		return isCountry;
	}

	public boolean isBusinessRegion() {
		return isBusinessRegion;
	}

    public String getDescription() {
        return description;
    }


}
