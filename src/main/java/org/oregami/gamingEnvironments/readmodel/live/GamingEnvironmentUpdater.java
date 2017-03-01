package org.oregami.gamingEnvironments.readmodel.live;

import org.axonframework.eventhandling.EventHandler;
import org.oregami.gamingEnvironments.GamingEnvironmentRepository;
import org.oregami.gamingEnvironments.event.GamingEnvironmentCreatedEvent;
import org.oregami.gamingEnvironments.event.TitleAddedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by sebastian on 19.02.17.
 */
@Component
public class GamingEnvironmentUpdater {

    GamingEnvironmentRepository repository;

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
        Title t = new Title(UUID.randomUUID().toString(), event.getRegionId(), event.getTitle(), event.getLanguage(), event.getScript());
        g.addTitle(t);
        repository.save(g);
    }
}
