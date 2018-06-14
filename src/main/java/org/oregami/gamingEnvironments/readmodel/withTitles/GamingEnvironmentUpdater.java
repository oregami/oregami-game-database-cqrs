package org.oregami.gamingEnvironments.readmodel.withTitles;

import org.axonframework.eventhandling.EventHandler;
import org.oregami.gamingEnvironments.event.GamingEnvironmentCreatedEvent;
import org.oregami.gamingEnvironments.event.TitleAddedEvent;
import org.oregami.gamingEnvironments.event.TitleUsageAddedEvent;
import org.oregami.gamingEnvironments.model.GamingEnvironmentRepository;
import org.oregami.transliteratedString.model.TransliteratedStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by sebastian on 19.02.17.
 */
@Component
public class GamingEnvironmentUpdater {

    @Autowired //TODO use decoupled access (REST call) instead of direct repository call
    TransliteratedStringRepository transliteratedStringRepository;

    GamingEnvironmentRepository repository;

    @Autowired
    public GamingEnvironmentUpdater(GamingEnvironmentRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(GamingEnvironmentCreatedEvent event) {
        GamingEnvironment g = new GamingEnvironment(event.getId(), event.getWorkingTitle());
        g.setChangeTime(LocalDateTime.now());
        repository.save(g);
    }

    @EventHandler
    public void on(TitleAddedEvent event) {
        GamingEnvironment g = repository.findOne(event.getGamingEnvironmentId());
        String tsText = transliteratedStringRepository.findOne(event.getTransliteratedStringId()).getText();
        Title t = new Title(UUID.randomUUID().toString(), event.getTransliteratedStringId(), tsText);
        g.addTitle(t);
        repository.save(g);
    }


    @EventHandler
    public void on(TitleUsageAddedEvent event) {
        GamingEnvironment g = repository.findOne(event.getGamingEnvironmentId());
        for (Title gt: g.getGametitles()) {
            if (gt.getTransliteratedStringId()  .equals(event.getTitleId())) {
                TitleUsage tu = new TitleUsage(UUID.randomUUID().toString(), event.getRegion());
                gt.getTitleUsages().add(tu);
            }
        }
        repository.save(g);
    }
}
