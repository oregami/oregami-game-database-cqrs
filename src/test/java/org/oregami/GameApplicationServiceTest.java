package org.oregami;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.game.application.GameApplicationService;
import org.oregami.game.model.GameEntryType;
import org.oregami.game.model.ReleaseGroupReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by sebastian on 17.02.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest()
public class GameApplicationServiceTest {

    @Autowired
    GameApplicationService gameApplicationService;

    @Autowired
    EventStore eventStore;


    @Test
    public void createGame() throws ExecutionException, InterruptedException {
        String gameId = UUID.randomUUID().toString();
        CompletableFuture<Object> resultId = gameApplicationService.createNewGame(gameId, GameEntryType.GAME.name(), "t1");
        Assert.assertThat(resultId.get(), Matchers.equalTo(gameId));
    }


    @Test
    public void createGameAndAddReleaseGroup() throws ExecutionException, InterruptedException {
        String gameId = UUID.randomUUID().toString();
        CompletableFuture<Object> resultId = gameApplicationService.createNewGame(gameId, GameEntryType.GAME.name(), "t1");
        Assert.assertThat(resultId.get(), Matchers.equalTo(gameId));

        String releaseGroupId = UUID.randomUUID().toString();
        CompletableFuture<Object> result2 = gameApplicationService.addReleaseGroup(gameId, releaseGroupId, ReleaseGroupReason.ORIGINAL.name());

        CompletableFuture.allOf(result2).join();

        Assert.assertThat(result2.get(), Matchers.equalTo(releaseGroupId));

    }

}
