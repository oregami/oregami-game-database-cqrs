package org.oregami.regions.application;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.regions.RRegionRepository;
import org.oregami.regions.RegionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

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
        Assert.assertThat(((RegionId)resultId.get()).getValue(), Matchers.equalTo(id));
        Assert.assertThat(repository.findAll().size(), Matchers.equalTo(size+1));
    }

}