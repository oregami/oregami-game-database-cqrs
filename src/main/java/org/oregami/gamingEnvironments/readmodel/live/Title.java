package org.oregami.gamingEnvironments.readmodel.live;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.model.EntityId;
import org.oregami.common.BaseEntityUUID;
import org.oregami.common.types.Language;
import org.oregami.common.types.Script;
import org.oregami.regions.RegionId;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * Created by sebastian on 28.02.17.
 */
@Entity
@NoArgsConstructor
@Getter
public class Title extends BaseEntityUUID {


    @Column
    @Embedded
    private RegionId regionId;

    @Column
    private String title;

    @Column
    private Language language;

    @Column
    private Script script;

    public Title(String id, RegionId regionId, String title, Language language, Script script) {
        super(id);
        this.regionId = regionId;
        this.title = title;
        this.language = language;
        this.script = script;
    }
}
