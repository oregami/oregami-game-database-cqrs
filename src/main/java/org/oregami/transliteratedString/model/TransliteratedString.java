package org.oregami.transliteratedString.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.oregami.transliteratedString.command.CreateTransliteratedStringCommand;
import org.oregami.transliteratedString.event.TransliteratedStringCreatedEvent;

/**
 * Created by sebastian on 25.04.15.
 */
@NoArgsConstructor
@Aggregate
@EqualsAndHashCode
public class TransliteratedString {

    @AggregateIdentifier
    private String id;

    @CommandHandler
    public TransliteratedString(CreateTransliteratedStringCommand command) {
        AggregateLifecycle.apply(new TransliteratedStringCreatedEvent(command.getId(), command.getText(), command.getLanguage(), command.getScript()));
    }

    @EventSourcingHandler
    public void in(TransliteratedStringCreatedEvent event) {
        this.id = event.getNewId();
    }

    private String text;

    private Language language;

    private Script script;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public String getId() {
        return id;
    }
}
