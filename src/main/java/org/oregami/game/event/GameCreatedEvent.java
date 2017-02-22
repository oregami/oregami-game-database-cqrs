package org.oregami.game.event;

import lombok.Getter;
import org.oregami.game.GameEntryType;


/**
 * Created by sebastian on 03.11.16.
 */
@Getter
public class GameCreatedEvent {

    private final String workingTitle;
    private String gameId;

    private GameEntryType gameEntryType;



    public GameCreatedEvent(String gameId, GameEntryType gameEntryType, String workingTitle) {
        this.gameId = gameId;
        this.gameEntryType = gameEntryType;
        this.workingTitle = workingTitle;
    }
}
