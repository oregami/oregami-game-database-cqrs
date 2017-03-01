package org.oregami.game.readmodel;

import lombok.NoArgsConstructor;
import org.oregami.common.BaseEntityUUID;
import org.oregami.game.ReleaseGroupReason;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by sebastian on 16.12.16.
 */
@NoArgsConstructor
@Entity
public class RReleaseGroup extends BaseEntityUUID {

    @Column
    private ReleaseGroupReason releaseGroupReason;

    public RReleaseGroup(String id, ReleaseGroupReason releaseGroupReason) {
        super(id);
        this.releaseGroupReason = releaseGroupReason;
    }

    public ReleaseGroupReason getReleaseGroupReason() {
        return releaseGroupReason;
    }
}
