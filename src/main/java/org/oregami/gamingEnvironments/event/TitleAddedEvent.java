package org.oregami.gamingEnvironments.event;

import lombok.Getter;

/**
 * Created by sebastian on 28.02.17.
 */
@Getter
public class TitleAddedEvent {

    private String gamingEnvironmentId;
    private String transliteratedStringId;

    public TitleAddedEvent(String gamingEnvironmentId, String transliteratedStringId) {
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.transliteratedStringId = transliteratedStringId;
    }

}
