package org.oregami.game.commands;

import lombok.Getter;
import lombok.Setter;
import org.oregami.game.GameEntryType;

/**
 * Created by sebastian on 03.11.16.
 */
@Setter
@Getter
public class CreateGameCommand {

    private String id;
    private GameEntryType gameEntryType;

    public CreateGameCommand(String id, GameEntryType gameEntryType) {
        this.id = id;
        this.gameEntryType = gameEntryType;
    }

}
