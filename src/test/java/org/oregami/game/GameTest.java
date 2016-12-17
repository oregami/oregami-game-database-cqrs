package org.oregami.game;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.oregami.game.commands.AddReleaseGroupCommand;
import org.oregami.game.commands.CreateGameCommand;
import org.oregami.game.events.GameCreatedEvent;
import org.oregami.game.events.ReleaseGroupAddedEvent;

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
        fixture.givenNoPriorActivity()
                .when(new CreateGameCommand(id, GameEntryType.GAME))
        .expectEvents(new GameCreatedEvent(id, GameEntryType.GAME))
        ;

    }

    @Test
    public void addReleaseGroup() throws Exception {
        String gameId = UUID.randomUUID().toString();
        String rgId = UUID.randomUUID().toString();
        fixture.given(new GameCreatedEvent(gameId, GameEntryType.GAME))
                .when(new AddReleaseGroupCommand(gameId, rgId, ReleaseGroupReason.ORIGINAL))
                .expectEvents(new ReleaseGroupAddedEvent(gameId, rgId, ReleaseGroupReason.ORIGINAL))

        ;

    }


}