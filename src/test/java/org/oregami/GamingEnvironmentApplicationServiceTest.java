package org.oregami;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.game.GameEntryType;
import org.oregami.game.ReleaseGroupReason;
import org.oregami.game.application.GameApplicationService;
import org.oregami.gamingEnvironments.GamingEnvironmentRepository;
import org.oregami.gamingEnvironments.application.GamingEnvironmentApplicationService;
import org.oregami.gamingEnvironments.readmodel.live.GamingEnvironment;
import org.oregami.gamingEnvironments.readmodel.live.Title;
import org.oregami.regions.RRegionRepository;
import org.oregami.regions.RegionId;
import org.oregami.regions.application.RegionApplicationService;
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
    RRegionRepository regionRepository;

    @Autowired
    EventStore eventStore;

    @Autowired
    private RegionApplicationService regionApplicationService;

    @Autowired
    private GamingEnvironmentRepository gamingEnvironmentRepository;

    @Before
    public void init() {
        regionApplicationService.createNewRegion("NORTH_AMERICA", true, false, "this is _NORTH_AMERICA");
    }


    @Test
    public void createGamingEnvironment() throws ExecutionException, InterruptedException {
        String id = UUID.randomUUID().toString();
        CompletableFuture<Object> resultId = gamingEnvironmentApplicationService.createNewGamingEnvironment(id, "Mega Drive");
        Assert.assertThat(resultId.get(), Matchers.equalTo(id));
    }


    @Test
    public void createGamingEnvironmentAndAddTitle() throws ExecutionException, InterruptedException {

        long count = gamingEnvironmentRepository.count();

        String gamingEnvironmentId = UUID.randomUUID().toString();
        CompletableFuture<Object> resultId = gamingEnvironmentApplicationService.createNewGamingEnvironment(gamingEnvironmentId, "Mega Drive");
        Assert.assertThat(resultId.get(), Matchers.equalTo(gamingEnvironmentId));

        RegionId regionId = new RegionId(regionRepository.findOne("NORTH_AMERICA").getId());
        CompletableFuture<Object> resultId2 = gamingEnvironmentApplicationService.addTitle(gamingEnvironmentId, regionId, "Super Nintendo", "ENGLISH", "LATIN");

        Assert.assertThat(gamingEnvironmentRepository.count(), Matchers.is(count+1));

        GamingEnvironment one = gamingEnvironmentRepository.findOne(gamingEnvironmentId);
        Assert.assertThat(one.getId(), Matchers.is(gamingEnvironmentId));
        Assert.assertThat(one.getGametitles().size(), Matchers.is(1));
        Title title = one.getGametitles().iterator().next();
        Assert.assertThat(title.getRegionId().getValue(), Matchers.is("NORTH_AMERICA"));



    }




}
