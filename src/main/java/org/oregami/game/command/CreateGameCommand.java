package org.oregami.game.command;

import org.oregami.game.model.GameEntryType;

/**
 * Created by sebastian on 03.11.16.
 */
public class CreateGameCommand {

    private final String workingTitle;
    private String id;
    private GameEntryType gameEntryType;

    public CreateGameCommand(String id, GameEntryType gameEntryType, String workingTitle) {
        this.id = id;
        this.gameEntryType = gameEntryType;
        this.workingTitle = workingTitle;
    }

    public String getId() {
        return id;
    }

    public GameEntryType getGameEntryType() {
        return gameEntryType;
    }

    public String getWorkingTitle() {
        return workingTitle;
    }
}
