package org.oregami;

/**
 * Created by sebastian on 13.12.16.
 */
public class ReleaseGroupAddedEvent {


    private String gameId;
    private final String releaseGroupId;
    private final ReleaseGroupReason releaseGroupReason;


    public ReleaseGroupAddedEvent(String gameId, String releaseGroupId, ReleaseGroupReason releaseGroupReason) {
        this.gameId = gameId;
        this.releaseGroupId = releaseGroupId;
        this.releaseGroupReason = releaseGroupReason;
    }

    public String getReleaseGroupId() {
        return releaseGroupId;
    }

    public ReleaseGroupReason getReleaseGroupReason() {
        return releaseGroupReason;
    }

    public String getGameId() {
        return gameId;
    }
}
