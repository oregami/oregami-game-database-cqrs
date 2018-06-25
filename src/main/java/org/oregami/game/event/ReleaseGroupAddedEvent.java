package org.oregami.game.event;

import lombok.Getter;
import org.oregami.game.model.ReleaseGroupReason;

/**
 * Created by sebastian on 13.12.16.
 */
@Getter
public class ReleaseGroupAddedEvent {


    private final String gameId;
    private final String newReleaseGroupId;
    private final ReleaseGroupReason releaseGroupReason;


    public ReleaseGroupAddedEvent(String gameId, String newReleaseGroupId, ReleaseGroupReason releaseGroupReason) {
        this.gameId = gameId;
        this.newReleaseGroupId = newReleaseGroupId;
        this.releaseGroupReason = releaseGroupReason;
    }


}
