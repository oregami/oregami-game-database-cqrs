package org.oregami.game.adapter;

import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.oregami.game.RGameRepository;
import org.oregami.game.application.GameApplicationService;
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

    @Autowired
    private GameApplicationService gameApplicationService;

    @Autowired
    private EventStore eventStore;

    @Autowired
    private RGameRepository gameRepository;

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
        DomainEventStream domainEventStream = eventStore.readEvents(gameId);
        Iterator<? extends DomainEventMessage<?>> iterator = domainEventStream.asStream().iterator();
        List<DomainEventMessage> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }

    @GetMapping
    public String  getAll(Model model) {
        model.addAttribute("list", gameRepository.findAll());
        return "games/list";
    }

    @GetMapping(value = "/{gameId}")
    public String getOne(@PathVariable String gameId, Model model) {
        RGame game = gameRepository.findOne(gameId);
        model.addAttribute("game", game);
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



}
