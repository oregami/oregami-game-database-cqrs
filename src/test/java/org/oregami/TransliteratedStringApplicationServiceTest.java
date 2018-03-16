package org.oregami;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oregami.transliteratedString.model.Language;
import org.oregami.transliteratedString.model.Script;
import org.oregami.transliteratedString.model.TransliteratedStringRepository;
import org.oregami.transliteratedString.application.TransliteratedStringApplicationService;
import org.oregami.transliteratedString.readmodel.live.TransliteratedString;
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
public class TransliteratedStringApplicationServiceTest {

    @Autowired
    TransliteratedStringApplicationService applicationService;

    @Autowired
    TransliteratedStringRepository repository;

    @Autowired
    EventStore eventStore;


    @Test
    public void createNewTransliteratedString() throws ExecutionException, InterruptedException {
        String id = UUID.randomUUID().toString();
        CompletableFuture<Object> resultId = applicationService.createNewTransliteratedString(id, "Mega Drive", "ENGLISH", "LATIN");
        Assert.assertThat(resultId.get(), Matchers.equalTo(id));

        TransliteratedString one = repository.findOne(id);

        Assert.assertThat(one, Matchers.notNullValue());
        Assert.assertThat(one.getId(), Matchers.is(id));
        Assert.assertThat(one.getText(), Matchers.is("Mega Drive"));
        Assert.assertThat(one.getLanguage(), Matchers.is(Language.ENGLISH));
        Assert.assertThat(one.getScript(), Matchers.is(Script.LATIN));

    }



}
