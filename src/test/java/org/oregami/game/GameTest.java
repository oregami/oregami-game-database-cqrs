package org.oregami.game;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import org.oregami.api.ChangeNameCommand;
import org.oregami.api.CreateGameCommand;
import org.oregami.api.GameCreatedEvent;
import org.oregami.api.NameChangedEvent;

import static org.junit.Assert.*;

/**
 * Created by sebastian on 03.11.16.
 */
public class GameTest {
    private FixtureConfiguration<Game> fixture;

    @Before
    public void setUp() throws Exception {
    fixture = Fixtures.newGivenWhenThenFixture(Game.class);
    }

    @Test
    public void createGame() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new CreateGameCommand("1234", "Lemmings"))
        .expectEvents(new GameCreatedEvent("1234", "Lemmings"))
        ;

    }


    @Test
    public void changeName() {
        fixture.given(new GameCreatedEvent("1234", "Lemmings"))
                .when(new ChangeNameCommand("1234", "Lemmings 2"))
                .expectEvents(new NameChangedEvent("1234", "Lemmings 2"));
    }

    @Test
    public void changeNameTwice() {
        fixture.given(new GameCreatedEvent("1234", "Lemmings"), new ChangeNameCommand("1234", "Lemmings 2"))
                .when(new ChangeNameCommand("1234", null))
                .expectNoEvents()
                .expectException(RuntimeException.class);
    }

    @Test
    public void changeNameToNull() {
        fixture.given(new GameCreatedEvent("1234", "Lemmings"))
                .when(new ChangeNameCommand("1234", null))
                .expectNoEvents()
                .expectException(RuntimeException.class);
    }
}