/*******************************************************************************
 * Copyright (C) 2012  Oregami.org, Germany http://www.oregami.org
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of version 3 or any later version of the
 * 	GNU Affero General Public License as published by the Free Software
 * 	Foundation.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU Affero General Public License for more details.
 *
 * 	You should have received a copy of the GNU Affero General Public License
 * 	along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.oregami.game.readmodel;

import lombok.NoArgsConstructor;
import org.oregami.game.GameEntryType;
import org.oregami.basic.BaseEntityUUID;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class RGame extends BaseEntityUUID {

    private static final long serialVersionUID = -2362683596950421365L;
    private String workingTitle;
    private GameEntryType gameEntryType;

    public RGame(String id, GameEntryType gameEntryType, String workingTitle) {
        super(id);
        this.gameEntryType = gameEntryType;
        this.workingTitle = workingTitle;
    }


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn
    private Set<RReleaseGroup> releaseGroupList = new HashSet<>();

    @Column
    private LocalDateTime changeTime;

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    public void addReleaseGroup(RReleaseGroup vog) {
        this.releaseGroupList.add(vog);
    }

    public Collection<RReleaseGroup> getReleaseGroupList() {
        return releaseGroupList;
    }

    public GameEntryType getGameEntryType() {
        return gameEntryType;
    }

    public void setGameEntryType(GameEntryType gameEntryType) {
        this.gameEntryType = gameEntryType;
    }

    public String getWorkingTitle() {
        return workingTitle;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }
}
