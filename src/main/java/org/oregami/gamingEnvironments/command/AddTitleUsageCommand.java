package org.oregami.gamingEnvironments.command;

import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.oregami.gamingEnvironments.model.Region;

/**
 * Created by sebastian on 28.02.17.
 */
@Getter
public class AddTitleUsageCommand {

    @TargetAggregateIdentifier
    private String gamingEnvironmentId;

    private String titleId;
    private Region region;


    public AddTitleUsageCommand(String gamingEnvironmentId, String titleId, Region region) {
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.titleId = titleId;
        this.region = region;
    }

}
