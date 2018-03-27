package org.oregami.game.adapter;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.oregami.game.application.GameApplicationService;
import org.oregami.game.model.RGameRepository;
import org.oregami.game.readmodel.RGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 17.12.16.
 */
@RequestMapping("/games")
@Controller
public class GameResource {

    private GameApplicationService gameApplicationService;

    private EventStore eventStore;

    private RGameRepository rGameRepository;

    @Autowired
    public GameResource(GameApplicationService gameApplicationService, EventStore eventStore, RGameRepository rGameRepository) {
        this.gameApplicationService = gameApplicationService;
        this.eventStore = eventStore;
        this.rGameRepository = rGameRepository;
    }

    @PostMapping(value = "/createGame")
    public String createGame(@RequestParam String gameEntryType, @RequestParam String workingTitle, Model model) {
        String id = UUID.randomUUID().toString();
        CompletableFuture<Object> completableFuture = gameApplicationService.createNewGame(id, gameEntryType, workingTitle);
        model.addAttribute("gameId", id);
        return "games/created";
    }

    @PostMapping(value = "/{gameId}/addReleaseGroup")
    public String addReleaseGroup(@PathVariable String gameId, @RequestParam String releaseGroupReason, Model model) {
        String releaseGroupId = UUID.randomUUID().toString();
        gameApplicationService.addReleaseGroup(gameId, releaseGroupId, releaseGroupReason);
        model.addAttribute("gameId", gameId);
        return "games/update_done";
    }

    @GetMapping(value = "/{gameId}/events")
    public List<DomainEventMessage> getAllEvents(@PathVariable String gameId, Model model) {
        return getEventsForGame(gameId);
    }

    @GetMapping
    public String  getAll(Model model) {
        model.addAttribute("list", rGameRepository.findAll());
        return "games/list";
    }

    @GetMapping(value = "/{gameId}")
    public String getOne(@PathVariable String gameId, Model model) {
        RGame game = rGameRepository.findOne(gameId);
        model.addAttribute("game", game);
        model.addAttribute("events", getEventsForGameAsStrings(gameId));
        return "games/one";
    }

    @GetMapping(value = "/createGame")
    public String createGame(Model model) {
        return "games/create";
    }

    @GetMapping(value = "/{gameId}/addReleaseGroup")
    public String addReleaseGroup(@PathVariable String gameId, Model model) {
        model.addAttribute("gameId", gameId);
        return "games/addReleaseGroup";
    }



    private List<DomainEventMessage> getEventsForGame(String gameId) {
        List<DomainEventMessage> list = new ArrayList<>();
        DomainEventStream domainEventStream = eventStore.readEvents(gameId);
        Iterator<? extends DomainEventMessage<?>> iterator = domainEventStream.asStream().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }


    private List<Map<String, Object>> getEventsForGameAsStrings(String gameId) {

        List<Map<String, Object>> result = new ArrayList<>();
        DomainEventStream domainEventStream = eventStore.readEvents(gameId);
        Iterator<? extends DomainEventMessage<?>> iterator = domainEventStream.asStream().iterator();
        while (iterator.hasNext()) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            DomainEventMessage<?> event = iterator.next();
            map.put("Timestamp", event.getTimestamp());
            map.put("Payload",  event.getPayloadType().getSimpleName() + ": " + ToStringBuilder.reflectionToString(event.getPayload(), RecursiveToStringStyle.JSON_STYLE));
            map.put("MetaData", ToStringBuilder.reflectionToString(event.getMetaData(), RecursiveToStringStyle.JSON_STYLE));
            result.add(map);
        }
        return result;
    }


}
