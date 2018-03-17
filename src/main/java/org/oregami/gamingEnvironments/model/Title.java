package org.oregami.gamingEnvironments.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.model.EntityId;
import org.oregami.regions.RegionId;

/**
 * Created by sebastian on 28.02.17.
 */
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Title {

    @EntityId
    private String titleId;

    private String transliteratedStringId;

    public Title(String titleId, String transliteratedStringId) {
        this.titleId = titleId;
        this.transliteratedStringId = transliteratedStringId;
    }

}
