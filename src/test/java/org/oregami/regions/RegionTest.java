package org.oregami.regions;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.oregami.game.Game;
import org.oregami.game.GameEntryType;
import org.oregami.game.ReleaseGroupReason;
import org.oregami.game.command.AddReleaseGroupCommand;
import org.oregami.game.command.CreateGameCommand;
import org.oregami.game.event.GameCreatedEvent;
import org.oregami.game.event.ReleaseGroupAddedEvent;
import org.oregami.regions.command.CreateRegionCommand;
import org.oregami.regions.event.RegionCreatedEvent;

import java.util.UUID;

/**
 * Created by sebastian on 03.11.16.
 */
public class RegionTest {

    private FixtureConfiguration<Region> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Region.class);
    }

    @Test
    public void createRegion() throws Exception {
        RegionId id = new RegionId("GERMANY");
        fixture.givenNoPriorActivity()
                .when(new CreateRegionCommand(id, true, false, "descr"))
                .expectEvents(new RegionCreatedEvent(id, true, false, "descr"))
        ;

    }



}