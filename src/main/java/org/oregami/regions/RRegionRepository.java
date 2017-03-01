package org.oregami.regions;

import org.oregami.game.readmodel.RGame;
import org.oregami.regions.readmodel.RRegion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sebastian on 28.02.17.
 */
public interface RRegionRepository extends JpaRepository<RRegion, String> {
}
