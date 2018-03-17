package org.oregami.gamingEnvironments.event;

import lombok.Getter;
import org.oregami.regions.RegionId;

/**
 * Created by sebastian on 28.02.17.
 */
@Getter
public class TitleAddedEvent {

    private String gamingEnvironmentId;
    private String transliteratedStringId;
    private String text;

    public TitleAddedEvent(String gamingEnvironmentId, String transliteratedStringId, String text) {
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.transliteratedStringId = transliteratedStringId;
        this.text = text;
    }

}
