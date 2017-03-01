package org.oregami.game.event;

import lombok.Getter;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, RecursiveToStringStyle.JSON_STYLE);
    }
}
