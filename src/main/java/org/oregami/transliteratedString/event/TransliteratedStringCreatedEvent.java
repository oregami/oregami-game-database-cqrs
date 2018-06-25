package org.oregami.transliteratedString.event;

import org.oregami.transliteratedString.model.Language;
import org.oregami.transliteratedString.model.Script;

/**
 * Created by sebastian on 24.02.17.
 */
public class TransliteratedStringCreatedEvent {

    private final String newId;
    private final String text;
    private final Language language;
    private final Script script;

    public TransliteratedStringCreatedEvent(String id, String text, Language language, Script script) {
        this.newId = id;
        this.text = text;
        this.language = language;
        this.script = script;
    }

    public String getNewId() {
        return newId;
    }

    public String getText() {
        return text;
    }

    public Language getLanguage() {
        return language;
    }

    public Script getScript() {
        return script;
    }
}
