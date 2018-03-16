package org.oregami.gamingEnvironments.adapter;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.oregami.gamingEnvironments.application.GamingEnvironmentApplicationService;
import org.oregami.gamingEnvironments.model.GamingEnvironmentRepository;
import org.oregami.gamingEnvironments.readmodel.withTitles.GamingEnvironment;
import org.oregami.regions.RegionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 17.12.16.
 */
@RequestMapping("/gamingEnvironments")
@Controller
public class GamingEnvironmentResource {

    @Autowired
    private GamingEnvironmentApplicationService gamingEnvironmentApplicationService;

    @Autowired
    private EventStore eventStore;

    @Autowired
    private GamingEnvironmentRepository gamingEnvironmentRepository;

    @PostMapping(value = "/create")
    public String create(@RequestParam String workingTitle, Model model) {
        String id = UUID.randomUUID().toString();
        CompletableFuture<Object> completableFuture = gamingEnvironmentApplicationService.createNewGamingEnvironment(id, workingTitle);
        model.addAttribute("gamingEnvironmentId", id);
        return "gamingEnvironments/created";
    }

    @PostMapping(value = "/{gamingEnvironmentId}/addTitle")
    public String addTitle(@PathVariable String gamingEnvironmentId
            , @RequestParam String titleId
            , @RequestParam String regionId
            , Model model) {
        gamingEnvironmentApplicationService.addTitle(gamingEnvironmentId, new RegionId(regionId), titleId);
        model.addAttribute("gamingEnvironmentId", gamingEnvironmentId);
        return "gamingEnvironments/update_done";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("list", gamingEnvironmentRepository.findAll());
        return "gamingEnvironments/list";
    }

    @GetMapping(value = "/{gamingEnvironmentId}")
    public String getOne(@PathVariable String gamingEnvironmentId, Model model) {
        GamingEnvironment gamingEnvironment = gamingEnvironmentRepository.findOne(gamingEnvironmentId);
        model.addAttribute("gamingEnvironment", gamingEnvironment);
        model.addAttribute("events", getEventsForGamingEnvironmentAsStrings(gamingEnvironmentId));
        return "gamingEnvironments/one";
    }

    @GetMapping(value = "/create")
    public String createGame(Model model) {
        return "gamingEnvironments/create";
    }

    @GetMapping(value = "/{gamingEnvironmentId}/addTitle")
    public String addTitleForm(
            @PathVariable String gamingEnvironmentId,
            @RequestParam(value = "titleId", defaultValue = "") String titleId,
            Model model) {
        model.addAttribute("gamingEnvironmentId", gamingEnvironmentId);
        model.addAttribute("titleId", titleId);
        return "gamingEnvironments/addTitle";
    }




    private List<Map<String, Object>> getEventsForGamingEnvironmentAsStrings(String gamingEnvironmentId) {
        List<Map<String, Object>> result = new ArrayList<>();
        DomainEventStream domainEventStream = eventStore.readEvents(gamingEnvironmentId);
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
