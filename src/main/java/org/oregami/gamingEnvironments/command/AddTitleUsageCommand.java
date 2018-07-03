package org.oregami.gamingEnvironments.command;

import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.oregami.common.CommandValidator;
import org.oregami.gamingEnvironments.model.GamingEnvironmentValidator;
import org.oregami.gamingEnvironments.model.Region;
import org.oregami.gamingEnvironments.model.TitleType;

/**
 * Created by sebastian on 28.02.17.
 */
@Getter
@CommandValidator(GamingEnvironmentValidator.class)
public class AddTitleUsageCommand {


    @TargetAggregateIdentifier
    private String gamingEnvironmentId;

    private final String newId;

    private String titleId;
    private Region region;
    private final TitleType titlyType;

    public AddTitleUsageCommand(String newId, String gamingEnvironmentId, String titleId, Region region, TitleType titleType) {
        this.newId = newId;
        this.gamingEnvironmentId = gamingEnvironmentId;
        this.titleId = titleId;
        this.region = region;
        this.titlyType = titleType;
    }

}
