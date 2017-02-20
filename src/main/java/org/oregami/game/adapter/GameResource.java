package org.oregami.game.adapter;

import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.oregami.game.RGameRepository;
import org.oregami.game.application.GameApplicationService;
import org.oregami.game.readmodel.RGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 17.12.16.
 */
@RequestMapping("/games")
@RestController
public class GameResource {

    @Autowired
    private GameApplicationService gameApplicationService;

    @Autowired
    private EventStore eventStore;

    @Autowired
    private RGameRepository gameRepository;

    @RequestMapping(value = "/createGame", method = RequestMethod.POST)
    public CompletableFuture<Object> createGame(@RequestParam String gameEntryType) {
        String id = UUID.randomUUID().toString();
        return gameApplicationService.createNewGame(id, gameEntryType);
    }

    @RequestMapping(value = "/{gameId}/addReleaseGroup", method = RequestMethod.POST)
    public CompletableFuture<Object> addReleaseGroup(@PathVariable String gameId, @RequestParam String releaseGroupReason) {
        String releaseGroupId = UUID.randomUUID().toString();
        return gameApplicationService.addReleaseGroup(gameId, releaseGroupId, releaseGroupReason);
    }

    @GetMapping(value = "/{gameId}/events")
    public List<DomainEventMessage> getAllEvents(@PathVariable String gameId) {
        DomainEventStream domainEventStream = eventStore.readEvents(gameId);
        Iterator<? extends DomainEventMessage<?>> iterator = domainEventStream.asStream().iterator();
        List<DomainEventMessage> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }

    @GetMapping
    public List<RGame> getAll() {
        return gameRepository.findAll();
    }

    @GetMapping(value = "/{gameId}")
    public RGame getOne(@PathVariable String gameId) {
        return gameRepository.findOne(gameId);
    }


}
