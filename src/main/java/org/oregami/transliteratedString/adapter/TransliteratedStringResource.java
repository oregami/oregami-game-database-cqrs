package org.oregami.transliteratedString.adapter;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.oregami.transliteratedString.application.TransliteratedStringApplicationService;
import org.oregami.transliteratedString.model.TransliteratedStringRepository;
import org.oregami.transliteratedString.readmodel.live.TransliteratedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
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
            @RequestParam(value = "backUrl", defaultValue = "") String backUrl,
            Model model) {
        String id = UUID.randomUUID().toString();
        CompletableFuture<Object> completableFuture = transliteratedStringApplicationService.createNewTransliteratedString(id, text, language, script);
        model.addAttribute("transliteratedStringId", id);
        model.addAttribute("backUrl", backUrl);
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


    @GetMapping(value = "/search")
    public String search(
            @RequestParam String searchTitle,
            @RequestParam String backUrl,
            Model model) {
        model.addAttribute("searchTitle", searchTitle);
        model.addAttribute("backUrl", backUrl);

        List<TransliteratedString> transliteratedStrings = transliteratedStringRepository.findByTextIgnoreCaseContaining(searchTitle);
        model.addAttribute("transliteratedStrings", transliteratedStrings);

        return "transliteratedStrings/search";
    }




}
