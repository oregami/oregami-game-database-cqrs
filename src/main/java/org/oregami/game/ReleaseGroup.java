package org.oregami.game;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by sebastian on 16.12.16.
 */
@NoArgsConstructor
@EqualsAndHashCode
public class ReleaseGroup {


    private String releaseGroupId;

    private ReleaseGroupReason releaseGroupReason;

    public ReleaseGroup(String id, ReleaseGroupReason releaseGroupReason) {
        this.releaseGroupId = id;
        this.releaseGroupReason = releaseGroupReason;
    }
}
