package org.oregami.api;

import lombok.Getter;

/**
 * Created by sebastian on 03.11.16.
 */
@Getter
public class GameCreatedEvent {
    private String gameId;

    private String name;

    public GameCreatedEvent(String gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }
}
