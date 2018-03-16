package org.oregami.regions.readmodel;

import lombok.NoArgsConstructor;
import org.oregami.common.BaseEntityUUID;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class RRegion extends BaseEntityUUID {

    @Column
	private boolean isCountry;

    @Column
	private boolean isBusinessRegion;

    @Column
	private String description;

    public RRegion(String name, boolean isCountry, boolean isBusinessRegion, String description) {
        super(name);
        this.isCountry = isCountry;
        this.isBusinessRegion = isBusinessRegion;
        this.description = description;
    }
}
