package org.oregami.gamingEnvironments.event;

import lombok.Getter;
import org.oregami.gamingEnvironments.model.Region;

@Getter
public class TitleUsageAddedEvent {

    private Region region;
    private String gamingEnvironmentId;
    private String titleId;

    public TitleUsageAddedEvent(String gamingEnvironmentId, String titleId, Region region) {
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.titleId = titleId;
        this.region = region;
    }

}
