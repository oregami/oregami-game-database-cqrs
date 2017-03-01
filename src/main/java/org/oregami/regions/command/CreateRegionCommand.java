package org.oregami.regions.command;

import org.oregami.regions.RegionId;

/**
 * Created by sebastian on 28.02.17.
 */
public class CreateRegionCommand {

    private RegionId name;
    private boolean isCountry;
    private boolean isBusinessRegion;
    private String descrition;

    public CreateRegionCommand(RegionId name, boolean isCountry, boolean isBusinessRegion, String descrition) {
        this.name = name;
        this.isCountry = isCountry;
        this.isBusinessRegion = isBusinessRegion;
        this.descrition = descrition;
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

    public String getDescrition() {
        return descrition;
    }
}
