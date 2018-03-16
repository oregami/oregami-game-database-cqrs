package org.oregami.transliteratedString.readmodel.live;

import org.axonframework.eventhandling.EventHandler;
import org.oregami.transliteratedString.event.TransliteratedStringCreatedEvent;
import org.oregami.transliteratedString.model.TransliteratedStringRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
