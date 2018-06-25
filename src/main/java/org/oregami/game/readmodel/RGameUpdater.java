package org.oregami.game.readmodel;

import org.axonframework.eventhandling.EventHandler;
import org.oregami.game.event.GameCreatedEvent;
import org.oregami.game.event.ReleaseGroupAddedEvent;
import org.oregami.game.model.RGameRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by sebastian on 19.02.17.
 */
@Component
public class RGameUpdater {

    RGameRepository gameRepository;

    public RGameUpdater(RGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @EventHandler
    public void on(GameCreatedEvent event) {
        RGame g = new RGame(event.getNewId(), event.getGameEntryType(), event.getWorkingTitle());
        g.setChangeTime(LocalDateTime.now());
        gameRepository.save(g);
    }

    @EventHandler
    public void on(ReleaseGroupAddedEvent event) {
        RGame game = gameRepository.findOne(event.getGameId());
        RReleaseGroup releaseGroup = new RReleaseGroup(event.getNewReleaseGroupId(), event.getReleaseGroupReason());
        game.addReleaseGroup(releaseGroup);
        game.setChangeTime(LocalDateTime.now());
        gameRepository.save(game);
    }
}
