package org.oregami.gamingEnvironments.event;

import lombok.Getter;
import org.oregami.gamingEnvironments.model.Region;
import org.oregami.gamingEnvironments.model.TitleType;

@Getter
public class TitleUsageAddedEvent {

    private Region region;
    private String gamingEnvironmentId;
    private String titleId;
    private TitleType titleType;

    public TitleUsageAddedEvent(String gamingEnvironmentId, String titleId, Region region, TitleType titleType) {
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.titleId = titleId;
        this.region = region;
        this.titleType = titleType;
    }

}
