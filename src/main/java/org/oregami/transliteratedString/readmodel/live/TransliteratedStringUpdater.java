package org.oregami.transliteratedString.readmodel.live;

import org.axonframework.eventhandling.EventHandler;
import org.oregami.gamingEnvironments.GamingEnvironmentRepository;
import org.oregami.gamingEnvironments.event.GamingEnvironmentCreatedEvent;
import org.oregami.gamingEnvironments.event.TitleAddedEvent;
import org.oregami.gamingEnvironments.readmodel.live.GamingEnvironment;
import org.oregami.gamingEnvironments.readmodel.live.Title;
import org.oregami.transliteratedString.TransliteratedStringRepository;
import org.oregami.transliteratedString.event.TransliteratedStringCreatedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by sebastian on 19.02.17.
 */
@Component
public class TransliteratedStringUpdater {

    TransliteratedStringRepository repository;

    public TransliteratedStringUpdater(TransliteratedStringRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(TransliteratedStringCreatedEvent event) {
        TransliteratedString g = new TransliteratedString(event.getId(), event.getText(), event.getLanguage(), event.getScript());
        g.setChangeTime(LocalDateTime.now());
        repository.save(g);
    }

}
