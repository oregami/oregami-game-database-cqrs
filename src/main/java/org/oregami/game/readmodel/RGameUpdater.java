package org.oregami.game.readmodel;

import org.axonframework.eventhandling.EventHandler;
import org.oregami.game.RGameRepository;
import org.oregami.game.ReleaseGroup;
import org.oregami.game.event.GameCreatedEvent;
import org.oregami.game.event.ReleaseGroupAddedEvent;
import org.springframework.stereotype.Component;

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
        RGame g = new RGame(event.getGameId(), event.getGameEntryType());
        gameRepository.save(g);
    }

    @EventHandler
    public void on(ReleaseGroupAddedEvent event) {
        RGame game = gameRepository.findOne(event.getGameId());
        RReleaseGroup releaseGroup = new RReleaseGroup(event.getReleaseGroupId(), event.getReleaseGroupReason());
        game.addReleaseGroup(releaseGroup);
        gameRepository.save(game);
    }
}
