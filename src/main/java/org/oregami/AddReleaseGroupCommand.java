package org.oregami;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by sebastian on 03.11.16.
 */
@Setter
@Getter
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

}
