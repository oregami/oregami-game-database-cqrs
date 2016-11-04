package org.oregami.api;

import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by sebastian on 03.11.16.
 */
@Getter
public class ChangeNameCommand {

    @TargetAggregateIdentifier
    private String gameId;

    private String name;

    public ChangeNameCommand(String gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }
}
