package org.oregami.gamingEnvironments.command;

import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by sebastian on 28.02.17.
 */
@Getter
public class AddTitleCommand {

    @TargetAggregateIdentifier
    private String gamingEnvironmentId;

    private final String newId;

    private String transliteratedStringId;
    private String transliteratedStringText;


    public AddTitleCommand(String newId, String gamingEnvironmentId, String transliteratedStringId, String transliteratedStringText) {
        this.newId = newId;
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.transliteratedStringId = transliteratedStringId;
        this.transliteratedStringText = transliteratedStringText;
    }

}
