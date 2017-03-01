package org.oregami.gamingEnvironments;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.model.EntityId;
import org.oregami.common.types.Language;
import org.oregami.common.types.Script;
import org.oregami.regions.RegionId;

/**
 * Created by sebastian on 28.02.17.
 */
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Title {

    @EntityId
    private String titleId;

    private RegionId regionId;
    private String title;
    private Language language;
    private Script script;

    public Title(String titleId, RegionId regionId, String title, Language language, Script script) {
        this.titleId = titleId;
        this.regionId = regionId;
        this.title = title;
        this.language = language;
        this.script = script;
    }

}
