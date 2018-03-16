package org.oregami.transliteratedString.command;

import org.oregami.transliteratedString.model.Language;
import org.oregami.transliteratedString.model.Script;

/**
 * Created by sebastian on 24.02.17.
 */
public class CreateTransliteratedStringCommand {

    private final String id;
    private final String text;
    private final Language language;
    private final Script script;

    public CreateTransliteratedStringCommand(String id, String text, Language language, Script script) {
        this.id = id;
        this.text = text;
        this.language = language;
        this.script = script;
    }

    public String getId() {
        return id;
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
