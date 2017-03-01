package org.oregami.regions.readmodel;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.oregami.common.BaseEntityUUID;
import org.oregami.regions.command.CreateRegionCommand;
import org.oregami.regions.event.RegionCreatedEvent;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class RRegion extends BaseEntityUUID {

    @Column
	private boolean isCountry;

    @Column
	private boolean isBusinessRegion;

    @Column
	private String description;

    public RRegion(String name, boolean isCountry, boolean isBusinessRegion, String description) {
        super(name);
        this.isCountry = isCountry;
        this.isBusinessRegion = isBusinessRegion;
        this.description = description;
    }
}
