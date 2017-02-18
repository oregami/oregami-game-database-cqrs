package org.oregami.game.adapter;

import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.oregami.game.application.GameApplicationService;
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

    @RequestMapping(value = "/createGame", method = RequestMethod.POST)
    public CompletableFuture<Object> createGame(@RequestBody Map<String, String> request) {
        String id = UUID.randomUUID().toString();
        return gameApplicationService.createNewGame(id,request.get("gameEntryType"));
    }

    @RequestMapping(value = "/addReleaseGroup", method = RequestMethod.POST)
    public CompletableFuture<Object> addReleaseGroup(@RequestBody Map<String, String> request) {
        String gameId = request.get("gameId");
        String releaseGroupId = UUID.randomUUID().toString();
        return gameApplicationService.addReleaseGroup(gameId, releaseGroupId, request.get("releaseGroupReason"));
    }

    @GetMapping(value = "/{gameId}")
    public List<DomainEventMessage> getAll(@PathVariable String gameId) {
        DomainEventStream domainEventStream = eventStore.readEvents(gameId);
        Iterator<? extends DomainEventMessage<?>> iterator = domainEventStream.asStream().iterator();
        List<DomainEventMessage> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }


}
