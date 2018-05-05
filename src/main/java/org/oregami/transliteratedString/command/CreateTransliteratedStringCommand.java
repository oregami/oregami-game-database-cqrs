package org.oregami.transliteratedString.command;

import org.hibernate.validator.constraints.NotBlank;
import org.oregami.common.CommandValidator;
import org.oregami.transliteratedString.model.Language;
import org.oregami.transliteratedString.model.Script;
import org.oregami.transliteratedString.model.TransliteratedStringValidator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by sebastian on 24.02.17.
 */
@CommandValidator(TransliteratedStringValidator.class)
public class CreateTransliteratedStringCommand {

    private final String id;
    @NotNull(message = "First name is null")
    @NotBlank(message = "First name is blank")
    @Pattern(regexp = "[A-Z]*", message = "First name has invalid characters")
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
