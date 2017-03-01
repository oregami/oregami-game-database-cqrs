package org.oregami.regions.readmodel;

import org.axonframework.eventhandling.EventHandler;
import org.oregami.game.RGameRepository;
import org.oregami.game.event.GameCreatedEvent;
import org.oregami.game.event.ReleaseGroupAddedEvent;
import org.oregami.game.readmodel.RGame;
import org.oregami.game.readmodel.RReleaseGroup;
import org.oregami.regions.RRegionRepository;
import org.oregami.regions.event.RegionCreatedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by sebastian on 19.02.17.
 */
@Component
public class RRegionUpdater {

    RRegionRepository regionRepository;

    public RRegionUpdater(RRegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @EventHandler
    public void on(RegionCreatedEvent event) {
        RRegion r = new RRegion(event.getName().getValue(), event.isCountry(), event.isBusinessRegion(), event.getDescription());
        regionRepository.save(r);
    }

}
