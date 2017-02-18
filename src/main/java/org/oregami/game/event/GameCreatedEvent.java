package org.oregami.game.event;

import lombok.Getter;
import org.oregami.game.GameEntryType;


/**
 * Created by sebastian on 03.11.16.
 */
@Getter
public class GameCreatedEvent {

    private String gameId;

    private GameEntryType gameEntryType;

    public GameCreatedEvent(String gameId, GameEntryType gameEntryType) {
        this.gameId = gameId;
        this.gameEntryType = gameEntryType;
    }
}
