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

    private String transliteratedStringId;
    private String transliteratedStringText;


    public AddTitleCommand(String gamingEnvironmentId, String transliteratedStringId, String transliteratedStringText) {
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.transliteratedStringId = transliteratedStringId;
        this.transliteratedStringText = transliteratedStringText;
    }

}
