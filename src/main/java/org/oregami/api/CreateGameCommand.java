package org.oregami.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sebastian on 03.11.16.
 */
@Setter
@Getter
public class CreateGameCommand {

    private String gameId;

    private String name;

    public CreateGameCommand(String gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }
}
