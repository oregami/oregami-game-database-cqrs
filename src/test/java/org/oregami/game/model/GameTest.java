package org.oregami.game.model;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.oregami.game.command.AddReleaseGroupCommand;
import org.oregami.game.command.CreateGameCommand;
import org.oregami.game.event.GameCreatedEvent;
import org.oregami.game.event.ReleaseGroupAddedEvent;

import java.util.UUID;

/**
 * Created by sebastian on 03.11.16.
 */
public class GameTest {

    private FixtureConfiguration<Game> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Game.class);
    }

    @Test
    public void createGame() throws Exception {
        String id = UUID.randomUUID().toString();
        CreateGameCommand command = new CreateGameCommand(id, GameEntryType.GAME, "t1");
        GameCreatedEvent event = new GameCreatedEvent(id, GameEntryType.GAME, "t1");
        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(event)
        ;

    }

    @Test
    public void addReleaseGroup() throws Exception {
        String gameId = UUID.randomUUID().toString();
        String rgId = UUID.randomUUID().toString();
        fixture.given(new GameCreatedEvent(gameId, GameEntryType.GAME, "t1"))
                .when(new AddReleaseGroupCommand(gameId, rgId, ReleaseGroupReason.ORIGINAL))
                .expectEvents(new ReleaseGroupAddedEvent(gameId, rgId, ReleaseGroupReason.ORIGINAL))

        ;

    }


}