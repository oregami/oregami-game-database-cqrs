package org.oregami.gamingEnvironments.readmodel.withTitles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oregami.common.BaseEntityUUID;
import org.oregami.regions.RegionId;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * Created by sebastian on 28.02.17.
 */
@Entity
@NoArgsConstructor
@Getter
public class Title extends BaseEntityUUID {

    @Column
    private String transliteratedStringId;

    @Column
    private String transliteratedStringText;

    public Title(String id, String transliteratedStringId, String transliteratedStringText) {
        super(id);
        this.transliteratedStringId = transliteratedStringId;
        this.transliteratedStringText = transliteratedStringText;
    }
}
