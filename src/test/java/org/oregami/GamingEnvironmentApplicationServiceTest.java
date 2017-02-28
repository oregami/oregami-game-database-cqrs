package org.oregami;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.game.GameEntryType;
import org.oregami.game.ReleaseGroupReason;
import org.oregami.game.application.GameApplicationService;
import org.oregami.gamingEnvironments.application.GamingEnvironmentApplicationService;
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
public class GamingEnvironmentApplicationServiceTest {

    @Autowired
    GamingEnvironmentApplicationService gamingEnvironmentApplicationService;

    @Autowired
    EventStore eventStore;


    @Test
    public void createGamingEnvironment() throws ExecutionException, InterruptedException {
        String id = UUID.randomUUID().toString();
        CompletableFuture<Object> resultId = gamingEnvironmentApplicationService.createNewGamingEnvironment(id, "Mega Drive");
        Assert.assertThat(resultId.get(), Matchers.equalTo(id));
    }



}
