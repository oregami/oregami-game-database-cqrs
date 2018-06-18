package org.oregami.gamingEnvironments.event;

import lombok.Getter;

/**
 * Created by sebastian on 28.02.17.
 */
@Getter
public class TitleAddedEvent {

    private String newTitleId;
    private String gamingEnvironmentId;
    private String transliteratedStringId;

    public TitleAddedEvent(String newTitleId, String gamingEnvironmentId, String transliteratedStringId) {
        this.newTitleId = newTitleId;
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.transliteratedStringId = transliteratedStringId;
    }

}
