package org.oregami.game.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.oregami.game.model.ReleaseGroupReason;

/**
 * Created by sebastian on 03.11.16.
 */
public class AddReleaseGroupCommand {


    @TargetAggregateIdentifier
    private String gameId;

    private final String releaseGroupId;

    private final ReleaseGroupReason releaseGroupReason;

    public AddReleaseGroupCommand(String gameId, String releaseGroupId, ReleaseGroupReason releaseGroupReason) {
        this.gameId = gameId;
        this.releaseGroupId = releaseGroupId;
        this.releaseGroupReason = releaseGroupReason;
    }

    public String getGameId() {
        return gameId;
    }

    public String getReleaseGroupId() {
        return releaseGroupId;
    }

    public ReleaseGroupReason getReleaseGroupReason() {
        return releaseGroupReason;
    }
}
