package org.oregami.gamingEnvironments.command;

import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.oregami.common.types.Language;
import org.oregami.common.types.Script;
import org.oregami.regions.RegionId;

/**
 * Created by sebastian on 28.02.17.
 */
@Getter
public class AddTitleCommand {

    @TargetAggregateIdentifier
    private String gamingEnvironmentId;

    private RegionId regionId;
    private String transliteratedStringId;
    private String transliteratedStringText;


    public AddTitleCommand(String gamingEnvironmentId, RegionId regionId, String transliteratedStringId, String transliteratedStringText) {
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.regionId = regionId;
        this.transliteratedStringId = transliteratedStringId;
        this.transliteratedStringText = transliteratedStringText;
    }

}
