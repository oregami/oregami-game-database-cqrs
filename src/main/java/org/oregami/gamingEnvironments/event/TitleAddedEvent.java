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
    private String transliteratedStringId;
    private String text;

    public TitleAddedEvent(String gamingEnvironmentId, RegionId regionId, String transliteratedStringId, String text) {
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.regionId = regionId;
        this.transliteratedStringId = transliteratedStringId;
        this.text = text;
    }

}
