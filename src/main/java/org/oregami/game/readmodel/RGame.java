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
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.oregami.game.GameEntryType;
import org.oregami.game.ReleaseGroup;
import org.oregami.game.readmodel.BaseEntityUUID;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class RGame extends BaseEntityUUID {

    private static final long serialVersionUID = -2362683596950421365L;

    private GameEntryType gameEntryType;

    public RGame(String id, GameEntryType gameEntryType) {
        super(id);
        this.gameEntryType = gameEntryType;
    }


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn
    private Set<RReleaseGroup> releaseGroupList = new HashSet<>();

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

}
