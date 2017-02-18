package org.oregami.game;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.model.EntityId;

/**
 * Created by sebastian on 16.12.16.
 */
@NoArgsConstructor
@EqualsAndHashCode
public class ReleaseGroup {

    @EntityId
    private String releaseGroupId;

    private ReleaseGroupReason releaseGroupReason;

    public ReleaseGroup(String id, ReleaseGroupReason releaseGroupReason) {
        this.releaseGroupId = id;
        this.releaseGroupReason = releaseGroupReason;
    }

    public String getReleaseGroupId() {
        return releaseGroupId;
    }
}
