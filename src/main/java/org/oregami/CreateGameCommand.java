package org.oregami;

/**
 * Created by sebastian on 03.11.16.
 */
public class CreateGameCommand {

    private String id;
    private GameEntryType gameEntryType;

    public CreateGameCommand(String id, GameEntryType gameEntryType) {
        this.id = id;
        this.gameEntryType = gameEntryType;
    }

    public String getId() {
        return id;
    }

    public GameEntryType getGameEntryType() {
        return gameEntryType;
    }
}
