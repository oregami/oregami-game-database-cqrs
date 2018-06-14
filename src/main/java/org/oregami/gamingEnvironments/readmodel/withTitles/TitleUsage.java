package org.oregami.gamingEnvironments.readmodel.withTitles;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.model.EntityId;
import org.oregami.common.BaseEntityUUID;
import org.oregami.gamingEnvironments.model.Region;

import javax.persistence.Entity;

/**
 * Created by sebastian on 28.02.17.
 */
@Entity
@NoArgsConstructor
@Getter
public class TitleUsage extends BaseEntityUUID {

    private Region region;

    public TitleUsage(String id, Region region) {
        super(id);
        this.region = region;
    }

}
