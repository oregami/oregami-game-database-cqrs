package org.oregami.transliteratedString.adapter;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.oregami.transliteratedString.TransliteratedStringRepository;
import org.oregami.transliteratedString.application.TransliteratedStringApplicationService;
import org.oregami.transliteratedString.readmodel.live.TransliteratedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Created by sebastian on 17.12.16.
 */
@RequestMapping("/transliteratedStrings")
@Controller
public class TransliteratedStringResource {

    @Autowired
    private TransliteratedStringApplicationService transliteratedStringApplicationService;

    @Autowired
    private EventStore eventStore;

    @Autowired
    private TransliteratedStringRepository transliteratedStringRepository;

    @PostMapping(value = "/create")
    public String create(
            @RequestParam String text,
            @RequestParam String language,
            @RequestParam String script,
            Model model) {
        String id = UUID.randomUUID().toString();
        CompletableFuture<Object> completableFuture = transliteratedStringApplicationService.createNewTransliteratedString(id, text, language, script);
        model.addAttribute("transliteratedStringId", id);
        return "transliteratedStrings/created";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("list", transliteratedStringRepository.findAll());
        return "transliteratedStrings/list";
    }

    @GetMapping(value = "/{transliteratedStringId}")
    public String getOne(@PathVariable String transliteratedStringId, Model model) {
        TransliteratedString transliteratedString = transliteratedStringRepository.findOne(transliteratedStringId);
        model.addAttribute("transliteratedString", transliteratedString);
        return "transliteratedStrings/one";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        return "transliteratedStrings/create";
    }





}
