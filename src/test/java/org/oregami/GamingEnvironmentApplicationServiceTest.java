package org.oregami;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.gamingEnvironments.application.GamingEnvironmentApplicationService;
import org.oregami.gamingEnvironments.model.GamingEnvironmentRepository;
import org.oregami.gamingEnvironments.model.Region;
import org.oregami.gamingEnvironments.readmodel.withTitles.GamingEnvironment;
import org.oregami.gamingEnvironments.readmodel.withTitles.Title;
import org.oregami.gamingEnvironments.readmodel.withTitles.TitleUsage;
import org.oregami.transliteratedString.model.TransliteratedStringRepository;
import org.oregami.transliteratedString.readmodel.live.TransliteratedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.oregami.transliteratedString.model.Language.ENGLISH;
import static org.oregami.transliteratedString.model.Script.LATIN;

/**
 * Created by sebastian on 17.02.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GamingEnvironmentApplicationServiceTest {

    @Autowired
    GamingEnvironmentApplicationService gamingEnvironmentApplicationService;

    @Autowired
    EventStore eventStore;

    @Autowired
    private GamingEnvironmentRepository gamingEnvironmentRepository;

    @Autowired
    private TransliteratedStringRepository transliteratedStringRepository;


    @Test
    public void createGamingEnvironment() throws ExecutionException, InterruptedException {
        String id = UUID.randomUUID().toString();
        CompletableFuture<Object> resultId = gamingEnvironmentApplicationService.createNewGamingEnvironment(id, "Mega Drive");
        Assert.assertThat(resultId.get(), Matchers.equalTo(id));
    }


    @Test
    public void createGamingEnvironmentAndAddTitle() throws ExecutionException, InterruptedException {

        long count = gamingEnvironmentRepository.count();

        String gamingEnvironmentId = "ge1";
        CompletableFuture<Object> resultId = gamingEnvironmentApplicationService.createNewGamingEnvironment(gamingEnvironmentId, "Mega Drive");
        Assert.assertThat(resultId.get(), Matchers.equalTo(gamingEnvironmentId));

        String tsId1 = "tsId1";
        TransliteratedString ts1 = new TransliteratedString(tsId1, "text", ENGLISH, LATIN);
        String id1 = transliteratedStringRepository.saveAndFlush(ts1).getId();

        String newTitleId = "newTitleId";
        CompletableFuture<Object> resultTitle1 = gamingEnvironmentApplicationService.addTitle(newTitleId, gamingEnvironmentId, id1);
        Object resultTitleId1 = resultTitle1.get();

        Assert.assertThat(gamingEnvironmentRepository.count(), Matchers.is(count+1));

        GamingEnvironment one = gamingEnvironmentRepository.findOne(gamingEnvironmentId);
        Assert.assertThat(one.getId(), Matchers.is(gamingEnvironmentId));
        Assert.assertThat(one.getGametitles().size(), Matchers.is(1));

        Title title = one.getGametitles().iterator().next();
        Assert.assertThat(title.getId(), Matchers.is(resultTitleId1));

        /*
        CompletableFuture<Object> completableFuture = gamingEnvironmentApplicationService.addTitleUsage(gamingEnvironmentId, title.getId(), Region.EUROPE);

        Object result = completableFuture.get();

        GamingEnvironment loaded = gamingEnvironmentRepository.findOne(gamingEnvironmentId);

        Set<TitleUsage> titleUsages = loaded.getGametitles().iterator().next().getTitleUsages();
        Assert.assertThat(titleUsages.size(), Matchers.is(1));
        Assert.assertThat(titleUsages.iterator().next().getRegion(), Matchers.is(Region.EUROPE));
        */

    }




}
