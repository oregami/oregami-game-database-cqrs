package org.oregami.regions.event;

import org.oregami.regions.RegionId;

/**
 * Created by sebastian on 28.02.17.
 */
public class RegionCreatedEvent {

    private RegionId name;
    private boolean isCountry;
    private boolean isBusinessRegion;
    private String description;


    public RegionCreatedEvent(RegionId name, boolean isCountry, boolean isBusinessRegion, String description) {
        this.name = name;
        this.isCountry = isCountry;
        this.isBusinessRegion = isBusinessRegion;
        this.description = description;
    }

    public RegionId getName() {
        return name;
    }

    public boolean isCountry() {
        return isCountry;
    }

    public boolean isBusinessRegion() {
        return isBusinessRegion;
    }

    public String getDescription() {
        return description;
    }
}
