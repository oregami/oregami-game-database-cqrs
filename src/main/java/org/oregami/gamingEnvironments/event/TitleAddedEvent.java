package org.oregami.gamingEnvironments.event;

import lombok.Getter;
import org.oregami.common.types.Language;
import org.oregami.common.types.Script;
import org.oregami.regions.RegionId;

/**
 * Created by sebastian on 28.02.17.
 */
@Getter
public class TitleAddedEvent {

    private String gamingEnvironmentId;
    private RegionId regionId;
    private String title;
    private Language language;
    private Script script;

    public TitleAddedEvent(String gamingEnvironmentId, RegionId regionId, String title, Language language, Script script) {
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.regionId = regionId;
        this.title = title;
        this.language = language;
        this.script = script;
    }

}
