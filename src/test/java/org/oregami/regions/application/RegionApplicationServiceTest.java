package org.oregami.regions.application;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.game.application.GameApplicationService;
import org.oregami.regions.RRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;

/**
 * Created by sebastian on 28.02.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class RegionApplicationServiceTest {



    @Autowired
    RegionApplicationService regionApplicationService;

    @Autowired
    RRegionRepository repository;

    @Autowired
    EventStore eventStore;

    @Test
    public void createNewRegion() throws Exception {
        String id = "GERMANY";
        int size = repository.findAll().size();
        CompletableFuture<Object> resultId = regionApplicationService.createNewRegion(id, true, false, "a nice country");
        Assert.assertThat(resultId.get(), Matchers.equalTo(id));
        Assert.assertThat(repository.findAll().size(), Matchers.equalTo(size+1));
    }

}